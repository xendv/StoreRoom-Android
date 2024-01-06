package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.api.ProductsApi
import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productsApi: ProductsApi,
): ProductRepository {

    override suspend fun fetchProducts(): List<ProductItem> = withContext(Dispatchers.IO) {
        productsApi.getProducts()
    }
}