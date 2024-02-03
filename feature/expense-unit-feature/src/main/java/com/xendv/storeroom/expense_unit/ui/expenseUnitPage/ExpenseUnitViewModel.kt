package com.xendv.storeroom.expense_unit.ui.expenseUnitPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xendv.storeroom.expense_unit.ExpenseUnitStateUseCases
import com.xendv.storeroom.expense_unit.ExpenseUnitUseCases
import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.ui.pages.editPage.Editable
import com.xendv.storeroom.ui.pages.editPage.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class ExpenseUnitViewModel(
    private val productsUseCases: ExpenseUnitUseCases,
    private val productsStateUseCases: ExpenseUnitStateUseCases,
    private val navigator: Navigator,
) : ViewModel() {

    private val item by lazy { productsStateUseCases.currentExpenseUnitItem }

    val products: StateFlow<List<ExpenseUnitItem>> by lazy { productsUseCases.items }

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
                        productsUseCases.deleteItem(item = action.item)
                        updateItems()
                    }
                    is EditAction.Edit -> productsStateUseCases.showExpenseUnitPage(
                        expenseUnitItem = action.item
                    )
                    is EditAction.Update -> {
                        productsUseCases.updateItem(
                            item = action.editables.toItem()
                        )
                        updateItems()
                        navigator.popBack()
                    }
                }
            }.onFailure {
                Log.e("ExpenseUnitViewModel", "Network Error", it)
            }
        }
    }

    internal fun createEditables(item: ExpenseUnitItem? = null) = listOf(
        Editable.SingleEditable(
            title = "Id",
            value = item?.id,
            type = ValueType.INTEGER,
        ),
        Editable.SingleEditable(
            title = "Expense",
            value = item?.expense,
            type = ValueType.INTEGER,
        ),
        Editable.SingleEditable(
            title = "Unit",
            value = item?.unit,
            type = ValueType.BARCODE,
        ),
    )

    private suspend fun updateItems() {
        runCatching {
            productsUseCases.fetchItems()
        }.onFailure {
            Log.e("ExpenseUnitViewModel", "Network Error", it)
        }
    }

    private fun List<Editable>.toItem(): ExpenseUnitItem {
        val data = associate { it.title to it.value }
        return ExpenseUnitItem(
            id = data["Id"]?.toString()?.toIntOrNull(),
            expense = data["Expense"]?.toString()?.toIntOrNull(),
            unit = data["Unit"]?.toString(),
        )
    }
}