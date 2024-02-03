package com.xendv.storeroom.lot.ui.lotPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.lot.LotStateUseCases
import com.xendv.storeroom.lot.LotUseCases
import com.xendv.storeroom.lot.data.entities.LotItem
import com.xendv.storeroom.ui.pages.editPage.Editable
import com.xendv.storeroom.ui.pages.editPage.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class LotViewModel(
    private val lotUseCases: LotUseCases,
    private val lotStateUseCases: LotStateUseCases,
    private val navigator: Navigator,
) : ViewModel() {

    private val item by lazy { lotStateUseCases.currentLotItem }

    val lot: StateFlow<List<LotItem>> by lazy { lotUseCases.items }

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
                        lotUseCases.deleteItem(item = action.item)
                        updateItems()
                    }
                    is EditAction.Edit -> lotStateUseCases.showLotPage(
                        lotItem = action.item
                    )
                    is EditAction.Update -> {
                        lotUseCases.updateItem(
                            item = action.editables.toItem()
                        )
                        updateItems()
                        navigator.popBack()
                    }
                }
            }.onFailure {
                Log.e("LotViewModel", "Network Error", it)
            }
        }
    }

    internal fun createEditables(item: LotItem? = null) = listOf(
        Editable.SingleEditable(
            title = "Id",
            value = item?.id,
            type = ValueType.INTEGER,
        ),
        Editable.SingleEditable(
            title = "SKU",
            value = item?.sku,
            type = ValueType.TEXT,
        ),
        Editable.SingleEditable(
            title = "Supplier",
            value = item?.supplier,
            type = ValueType.INTEGER,
        ),
        Editable.SingleEditable(
            title = "Supply date",
            value = item?.supplyDate,
            type = ValueType.DATE,
        ),
        Editable.SingleEditable(
            title = "Expiration date",
            value = item?.expirationDate,
            type = ValueType.DATE,
        ),
    )

    private suspend fun updateItems() {
        runCatching {
            lotUseCases.fetchItems()
        }.onFailure {
            Log.e("LotViewModel", "Network Error", it)
        }
    }

    private fun List<Editable>.toItem(): LotItem {
        val data = associate { it.title to it.value }
        return LotItem(
            sku = data["SKU"]?.toString(),
            id = data["Id"]?.toString()?.toIntOrNull(),
            supplier = data["Supplier"]?.toString()?.toIntOrNull(),
            supplyDate = data["Supply date"]?.toString(),
            expirationDate = data["Expiration date"]?.toString(),
        )
    }
}