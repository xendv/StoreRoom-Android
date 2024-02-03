package com.xendv.storeroom.lot.data.repositories

import com.xendv.storeroom.lot.data.entities.LotItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class LotStateRepositoryImpl : LotStateRepository {

    private val _itemToShow: MutableStateFlow<LotItem?> = MutableStateFlow(null)

    private val _items: MutableStateFlow<List<LotItem>> = MutableStateFlow(emptyList())

    override val items: StateFlow<List<LotItem>> = _items.asStateFlow()

    override val itemToShow: Flow<LotItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: LotItem) {
        _itemToShow.emit(item)
    }

    override suspend fun setItems(items: List<LotItem>) {
        _items.emit(items)
    }
}