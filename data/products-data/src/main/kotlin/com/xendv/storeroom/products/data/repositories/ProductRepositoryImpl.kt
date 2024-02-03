package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.api.ProductsApi
import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productsApi: ProductsApi,
): ProductRepository {

    override suspend fun fetchItems(): List<ProductItem> = withContext(Dispatchers.IO) {
        productsApi.getProducts()
    }

    override suspend fun getItem(id: String): ProductItem = withContext(Dispatchers.IO) {
        productsApi.getProduct(id)
    }

    override suspend fun deleteItem(item: ProductItem): Boolean = withContext(Dispatchers.IO) {
        requireNotNull(item.sku)
        productsApi.deleteProduct(id = item.sku)
    }

    override suspend fun updateItem(item: ProductItem): ProductItem = withContext(Dispatchers.IO) {
        productsApi.updateProduct(item)
    }

    override suspend fun createItem(item: ProductItem): ProductItem = withContext(Dispatchers.IO) {
        productsApi.createProduct(item)
    }
}