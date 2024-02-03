package com.xendv.storeroom.products

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.products.data.repositories.ProductStateRepository

class ProductsStateUseCases(
    private val productsStateRepository: ProductStateRepository,
    private val navigator: Navigator,
) {
    val currentProductsItem = productsStateRepository.itemToShow

    suspend fun showProductsPage(
        productsItem: ProductItem,
    ) {
        productsStateRepository.setItemToShow(item = productsItem)
        navigator.navigate("products/edit")
    }
}
