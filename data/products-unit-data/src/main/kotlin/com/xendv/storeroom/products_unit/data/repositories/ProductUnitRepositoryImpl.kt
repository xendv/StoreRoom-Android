package com.xendv.storeroom.products_unit.data.repositories

import com.xendv.storeroom.products_unit.data.api.ProductUnitsApi
import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductUnitRepositoryImpl(
    private val productUnitApi: ProductUnitsApi,
) : ProductUnitRepository {

    override suspend fun fetchItems(): List<ProductUnitItem> = withContext(Dispatchers.IO) {
        productUnitApi.getProductUnits()
    }

    override suspend fun getItem(id: String): ProductUnitItem = withContext(Dispatchers.IO) {
        productUnitApi.getProductUnit(id)
    }

    override suspend fun deleteItem(item: ProductUnitItem): Boolean = withContext(Dispatchers.IO) {
        requireNotNull(item.sku)
        productUnitApi.deleteProductUnit(id = item.sku)
    }

    override suspend fun updateItem(item: ProductUnitItem): ProductUnitItem =
        withContext(Dispatchers.IO) {
            productUnitApi.updateProductUnit(item)
        }

    override suspend fun createItem(item: ProductUnitItem): ProductUnitItem =
        withContext(Dispatchers.IO) {
            productUnitApi.createProductUnit(item)
        }
}