package com.xendv.storeroom.lot

import com.xendv.storeroom.lot.data.entities.LotItem
import com.xendv.storeroom.lot.data.repositories.LotRepository
import com.xendv.storeroom.lot.data.repositories.LotStateRepository


class LotUseCases(
    private val lotRepository: LotRepository,
    private val productStateRepository: LotStateRepository,
) {

    val items = productStateRepository.items

    suspend fun fetchItems() {
        val lots = lotRepository.fetchItems()
        productStateRepository.setItems(lots)
    }

    suspend fun deleteItem(item: LotItem): Boolean {
        return lotRepository.deleteItem(item)
    }

    suspend fun updateItem(item: LotItem): LotItem {
        return lotRepository.updateItem(item)
    }

    suspend fun createItem(item: LotItem): LotItem {
        return lotRepository.createItem(item)
    }
}