package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.entities.ProductItem

interface ProductRepository {

    suspend fun fetchProducts(): List<ProductItem>

}