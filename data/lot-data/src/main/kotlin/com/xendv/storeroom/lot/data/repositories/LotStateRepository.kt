package com.xendv.storeroom.lot.data.repositories

import com.xendv.storeroom.lot.data.entities.LotItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LotStateRepository {

    val items: StateFlow<List<LotItem>>

    val itemToShow: Flow<LotItem>

    suspend fun setItemToShow(item: LotItem)

    suspend fun setItems(items: List<LotItem>)
}