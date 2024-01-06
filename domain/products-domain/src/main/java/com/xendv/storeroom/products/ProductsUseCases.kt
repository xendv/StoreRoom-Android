package com.stmegi.app.products.domain.usecases

import androidx.paging.cachedIn
import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.products.data.repositories.ProductRepository
import com.xendv.storeroom.products.data.utils.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class ProductsUseCases(
    private val productsRepository: ProductRepository,
) {

    fun getProducts(
        scope: CoroutineScope,
    ): StateFlow<List<ProductItem>> = flow {
        val products = productsRepository.fetchProducts()
        emit(products)
    }.cachedIn(scope)

    private companion object {
        const val INITIAL_PAGE_NUMBER = 1
        const val DEFAULT_PAGE_SIZE = 4
    }
}