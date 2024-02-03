package com.xendv.storeroom.products_unit

import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitRepository
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitStateRepository

class ProductUnitUseCases(
    private val productUnitRepository: ProductUnitRepository,
    private val productUnitStateRepository: ProductUnitStateRepository,
) {

    val items = productUnitStateRepository.items

    suspend fun fetchItems() {
        val products = productUnitRepository.fetchItems()
        productUnitStateRepository.setItems(products)
    }

    suspend fun deleteItem(item: ProductUnitItem): Boolean {
        return productUnitRepository.deleteItem(item)
    }

    suspend fun updateItem(item: ProductUnitItem): ProductUnitItem {
        return productUnitRepository.updateItem(item)
    }

    suspend fun createItem(item: ProductUnitItem): ProductUnitItem {
        return productUnitRepository.createItem(item)
    }
}