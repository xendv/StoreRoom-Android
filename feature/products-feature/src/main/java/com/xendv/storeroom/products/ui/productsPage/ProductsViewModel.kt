package com.xendv.storeroom.products.ui.productsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.stmegi.app.products.domain.usecases.ProductsStateUseCases
import com.stmegi.app.products.domain.usecases.ProductsUseCases
import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class ProductsViewModel(
    private val productsUseCases: ProductsUseCases,
    private val productsStateUseCases: ProductsStateUseCases,
) : ViewModel() {

    open val products: StateFlow<List<ProductItem>> by lazy {
        productsUseCases.getProducts(
            scope = viewModelScope
        )
    }

    fun onProductItemClicked(item: ProductItem, index: Int) {
        /*viewModelScope.launch {
            productsStateUseCases.showProductItemPage(
                productItemItem = item.withPagingContext(
                    data = productItemPaged,
                    index = index,
                    scope = viewModelScope,
                )
            )
        }*/
    }
}