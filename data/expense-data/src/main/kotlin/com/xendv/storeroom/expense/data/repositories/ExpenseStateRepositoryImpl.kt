package com.xendv.storeroom.expense.data.repositories

import com.xendv.storeroom.expense.data.entities.ExpenseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ExpenseStateRepositoryImpl : ExpenseStateRepository {

    private val _itemToShow: MutableStateFlow<ExpenseItem?> = MutableStateFlow(null)

    private val _items: MutableStateFlow<List<ExpenseItem>> = MutableStateFlow(emptyList())

    override val items: StateFlow<List<ExpenseItem>> = _items.asStateFlow()

    override val itemToShow: Flow<ExpenseItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: ExpenseItem) {
        _itemToShow.emit(item)
    }

    override suspend fun setItems(items: List<ExpenseItem>) {
        _items.emit(items)
    }
}