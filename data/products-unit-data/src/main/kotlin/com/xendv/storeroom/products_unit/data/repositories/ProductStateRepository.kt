package com.xendv.storeroom.products_unit.data.repositories

import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ProductUnitStateRepository {

    val items: StateFlow<List<ProductUnitItem>>

    val itemToShow: Flow<ProductUnitItem>

    suspend fun setItemToShow(item: ProductUnitItem)

    suspend fun setItems(items: List<ProductUnitItem>)
}