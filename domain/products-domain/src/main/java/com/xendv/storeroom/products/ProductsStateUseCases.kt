package com.stmegi.app.products.domain.usecases

import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.products.data.repositories.ProductStateRepository

class ProductsStateUseCases(
    private val productsStateRepository: ProductStateRepository,
) {
    val currentProductsItem = productsStateRepository.itemToShow

    suspend fun showProductsPage(
        productsItem: ProductItem,
    ) {
        productsStateRepository.setItemToShow(item = productsItem)

    }
}
