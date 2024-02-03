package com.xendv.storeroom.products.ui.productsPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.products.ProductsStateUseCases
import com.xendv.storeroom.products.ProductsUseCases
import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.ui.pages.editPage.Editable
import com.xendv.storeroom.ui.pages.editPage.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class ProductsViewModel(
    private val productsUseCases: ProductsUseCases,
    private val productsStateUseCases: ProductsStateUseCases,
    private val navigator: Navigator,
) : ViewModel() {

    private val item by lazy { productsStateUseCases.currentProductsItem }

    val products: StateFlow<List<ProductItem>> by lazy { productsUseCases.items }

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
                    is EditAction.Edit -> productsStateUseCases.showProductsPage(
                        productsItem = action.item
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
                Log.e("ProductsViewModel", "Network Error", it)
            }
        }
    }

    internal fun createEditables(item: ProductItem? = null) = listOf(
        Editable.SingleEditable(
            title = "SKU",
            value = item?.sku,
            type = ValueType.TEXT,
        ),
        Editable.SingleEditable(
            title = "Name",
            value = item?.name,
            type = ValueType.TEXT,
        ),
        Editable.SingleEditable(
            title = "Barcode",
            value = item?.barcode,
            type = ValueType.BARCODE,
        ),
        Editable.SingleEditable(
            title = "Description",
            value = item?.description,
            type = ValueType.TEXT,
        ),
        Editable.SingleEditable(
            title = "Measurement",
            value = item?.measurement,
            type = ValueType.INTEGER,
        ),
    )

    private suspend fun updateItems() {
        runCatching {
            productsUseCases.fetchItems()
        }.onFailure {
            Log.e("ProductsViewModel", "Network Error", it)
        }
    }

    private fun List<Editable>.toItem(): ProductItem {
        val data = associate { it.title to it.value }
        return ProductItem(
            sku = data["SKU"]?.toString(),
            barcode = data["Barcode"]?.toString(),
            name = data["Name"]?.toString(),
            description = data["Description"]?.toString(),
            measurement = data["Measurement"]?.toString(),
        )
    }
}