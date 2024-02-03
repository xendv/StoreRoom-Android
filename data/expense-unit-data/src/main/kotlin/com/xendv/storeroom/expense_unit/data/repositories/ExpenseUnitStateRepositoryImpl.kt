package com.xendv.storeroom.expense_unit.data.repositories

import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class ExpenseUnitStateRepositoryImpl : ExpenseUnitStateRepository {

    private val _itemToShow: MutableStateFlow<ExpenseUnitItem?> = MutableStateFlow(null)

    private val _items: MutableStateFlow<List<ExpenseUnitItem>> = MutableStateFlow(emptyList())

    override val items: StateFlow<List<ExpenseUnitItem>> = _items.asStateFlow()

    override val itemToShow: Flow<ExpenseUnitItem> = _itemToShow.filterNotNull()

    override suspend fun setItemToShow(item: ExpenseUnitItem) {
        _itemToShow.emit(item)
    }

    override suspend fun setItems(items: List<ExpenseUnitItem>) {
        _items.emit(items)
    }
}