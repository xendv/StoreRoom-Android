package com.xendv.storeroom.expense.ui.expensePage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.expense.ExpenseStateUseCases
import com.xendv.storeroom.expense.ExpenseUseCases
import com.xendv.storeroom.expense.data.entities.ExpenseItem
import com.xendv.storeroom.ui.pages.editPage.Editable
import com.xendv.storeroom.ui.pages.editPage.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class ExpenseViewModel(
    private val expenseUseCases: ExpenseUseCases,
    private val expenseStateUseCases: ExpenseStateUseCases,
    private val navigator: Navigator,
) : ViewModel() {

    private val item by lazy { expenseStateUseCases.currentExpenseItem }

    val expense: StateFlow<List<ExpenseItem>> by lazy { expenseUseCases.items }

    val editables: Flow<List<Editable>> by lazy {
        item.map { createEditables(it) }
    }

    init {
        viewModelScope.launch {
            updateItems()
        }
    }

    fun processEditAction(action: EditAction) {
        viewModelScope.launch {
            runCatching {
                when (action) {
                    is EditAction.Delete -> {
                        expenseUseCases.deleteItem(item = action.item)
                        updateItems()
                    }
                    is EditAction.Edit -> expenseStateUseCases.showExpensePage(
                        expenseItem = action.item
                    )
                    is EditAction.Update -> {
                        expenseUseCases.updateItem(
                            item = action.editables.toItem()
                        )
                        updateItems()
                        navigator.popBack()
                    }
                }
            }.onFailure {
                Log.e("ExpenseViewModel", "Network Error", it)
            }
        }
    }

    internal fun createEditables(item: ExpenseItem? = null) = listOf(
        Editable.SingleEditable(
            title = "Id",
            value = item?.id,
            type = ValueType.TEXT,
        ),
        Editable.ListEditable(
            title = "Units",
            value = item?.units ?: emptyList(),
            type = ValueType.BARCODE,
        ),
        Editable.SingleEditable(
            title = "Date",
            value = item?.date,
            type = ValueType.DATE,
        ),
    )

    private suspend fun updateItems() {
        runCatching {
            expenseUseCases.fetchItems()
        }.onFailure {
            Log.e("ExpenseViewModel", "Network Error", it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun List<Editable>.toItem(): ExpenseItem {
        val data = associate { it.title to it.value }
        return ExpenseItem(
            id = data["Id"]?.toString()?.toIntOrNull(),
            units = data["Units"] as? List<String>,
            date = data["Date"]?.toString(),
        )
    }
}