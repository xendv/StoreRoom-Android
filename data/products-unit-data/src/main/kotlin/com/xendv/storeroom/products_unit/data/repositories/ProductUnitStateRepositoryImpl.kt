package com.xendv.storeroom.products_unit.data.repositories

import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ProductUnitStateRepositoryImpl : ProductUnitStateRepository {

    private val _itemToShow: MutableStateFlow<ProductUnitItem?> = MutableStateFlow(null)

    private val _items: MutableStateFlow<List<ProductUnitItem>> = MutableStateFlow(emptyList())

    override val items: StateFlow<List<ProductUnitItem>> = _items.asStateFlow()

    override val itemToShow: Flow<ProductUnitItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: ProductUnitItem) {
        _itemToShow.emit(item)
    }

    override suspend fun setItems(items: List<ProductUnitItem>) {
        _items.emit(items)
    }
}