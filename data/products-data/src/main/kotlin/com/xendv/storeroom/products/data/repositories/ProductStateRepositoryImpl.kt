package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.products.data.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ProductStateRepositoryImpl : ProductStateRepository {

    private val _itemToShow: MutableStateFlow<ProductItem?> = MutableStateFlow(null)

    override val itemToShow: Flow<ProductItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: ProductItem) {
        _itemToShow.emit(item)
    }
}