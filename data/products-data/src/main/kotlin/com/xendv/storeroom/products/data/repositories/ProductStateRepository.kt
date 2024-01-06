package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductStateRepository {
    val itemToShow: Flow<ProductItem>

    suspend fun setItemToShow(item: ProductItem)
}