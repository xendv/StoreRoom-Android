package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ProductStateRepository {

    val items: StateFlow<List<ProductItem>>

    val itemToShow: Flow<ProductItem>

    suspend fun setItemToShow(item: ProductItem)

    suspend fun setItems(items: List<ProductItem>)
}