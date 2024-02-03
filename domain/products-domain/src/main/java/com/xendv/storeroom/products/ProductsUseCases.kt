package com.xendv.storeroom.products

import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.products.data.repositories.ProductRepository
import com.xendv.storeroom.products.data.repositories.ProductStateRepository

class ProductsUseCases(
    private val productsRepository: ProductRepository,
    private val productStateRepository: ProductStateRepository,
) {

    val items = productStateRepository.items

    suspend fun fetchItems() {
        val products = productsRepository.fetchItems()
        productStateRepository.setItems(products)
    }

    suspend fun deleteItem(item: ProductItem): Boolean {
        return productsRepository.deleteItem(item)
    }

    suspend fun updateItem(item: ProductItem): ProductItem {
        return productsRepository.updateItem(item)
    }

    suspend fun createItem(item: ProductItem): ProductItem {
        return productsRepository.createItem(item)
    }
}