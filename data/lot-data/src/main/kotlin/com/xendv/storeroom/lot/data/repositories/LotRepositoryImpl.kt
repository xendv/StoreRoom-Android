package com.xendv.storeroom.lot.data.repositories

import com.xendv.storeroom.lot.data.api.LotApi
import com.xendv.storeroom.lot.data.entities.LotItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LotRepositoryImpl(
    private val lotApi: LotApi,
): LotRepository {

    override suspend fun fetchItems(): List<LotItem> = withContext(Dispatchers.IO) {
        lotApi.getLot()
    }

    override suspend fun getItem(id: String): LotItem = withContext(Dispatchers.IO) {
        lotApi.getLot(id)
    }

    override suspend fun deleteItem(item: LotItem): Boolean = withContext(Dispatchers.IO) {
        requireNotNull(item.sku)
        lotApi.deleteLot(id = item.sku)
    }

    override suspend fun updateItem(item: LotItem): LotItem = withContext(Dispatchers.IO) {
        lotApi.updateLot(item)
    }

    override suspend fun createItem(item: LotItem): LotItem = withContext(Dispatchers.IO) {
        lotApi.createLot(item)
    }
}