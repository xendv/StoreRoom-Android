package com.xendv.storeroom.products_unit.ui.productsPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.products_unit.ProductUnitStateUseCases
import com.xendv.storeroom.products_unit.ProductUnitUseCases
import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import com.xendv.storeroom.ui.pages.editPage.Editable
import com.xendv.storeroom.ui.pages.editPage.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class ProductUnitViewModel(
    private val productUnitUseCases: ProductUnitUseCases,
    private val productUnitStateUseCases: ProductUnitStateUseCases,
    private val navigator: Navigator,
) : ViewModel() {

    private val item by lazy { productUnitStateUseCases.currentProductUnitItem }

    val productUnits: StateFlow<List<ProductUnitItem>> by lazy { productUnitUseCases.items }

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
                        productUnitUseCases.deleteItem(item = action.item)
                        updateItems()
                    }

                    is EditAction.Edit -> productUnitStateUseCases.showProductUnitsPage(
                        productUnitsItem = action.item
                    )

                    is EditAction.Update -> {
                        productUnitUseCases.updateItem(
                            item = action.editables.toItem()
                        )
                        updateItems()
                        navigator.popBack()
                    }
                }
            }.onFailure {
                Log.e("ProductsViewModel", "Network Error", it)
            }
        }
    }

    internal fun createEditables(item: ProductUnitItem? = null) = listOf(
        Editable.SingleEditable(
            title = "Id",
            value = item?.id,
            type = ValueType.BARCODE,
        ),
        Editable.SingleEditable(
            title = "SKU",
            value = item?.sku,
            type = ValueType.TEXT,
        ),
        Editable.SingleEditable(
            title = "Lot",
            value = item?.lot,
            type = ValueType.INTEGER,
        ),
    )

    private suspend fun updateItems() {
        runCatching {
            productUnitUseCases.fetchItems()
        }.onFailure {
            Log.e("ProductUnitViewModel", "Network Error", it)
        }
    }

    private fun List<Editable>.toItem(): ProductUnitItem {
        val data = associate { it.title to it.value }
        return ProductUnitItem(
            sku = data["SKU"]?.toString(),
            lot = data["Lot"]?.toString()?.toIntOrNull(),
            id = data["Id"]?.toString(),
        )
    }
}