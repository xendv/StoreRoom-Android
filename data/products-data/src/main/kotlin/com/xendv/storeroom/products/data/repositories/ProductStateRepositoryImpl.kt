package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ProductStateRepositoryImpl : ProductStateRepository {

    private val _itemToShow: MutableStateFlow<ProductItem?> = MutableStateFlow(null)

    private val _items: MutableStateFlow<List<ProductItem>> = MutableStateFlow(emptyList())

    override val items: StateFlow<List<ProductItem>> = _items.asStateFlow()

    override val itemToShow: Flow<ProductItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: ProductItem) {
        _itemToShow.emit(item)
    }

    override suspend fun setItems(items: List<ProductItem>) {
        _items.emit(items)
    }
}