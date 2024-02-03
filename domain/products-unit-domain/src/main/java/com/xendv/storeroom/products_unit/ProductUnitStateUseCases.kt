package com.xendv.storeroom.products_unit

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitStateRepository

class ProductUnitStateUseCases(
    private val productUnitsStateRepository: ProductUnitStateRepository,
    private val navigator: Navigator,
) {
    val currentProductUnitItem = productUnitsStateRepository.itemToShow

    suspend fun showProductUnitsPage(
        productUnitsItem: ProductUnitItem,
    ) {
        productUnitsStateRepository.setItemToShow(item = productUnitsItem)
        navigator.navigate("productUnit/edit")
    }
}
