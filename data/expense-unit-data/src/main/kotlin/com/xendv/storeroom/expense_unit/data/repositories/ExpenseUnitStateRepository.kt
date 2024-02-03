package com.xendv.storeroom.expense_unit.data.repositories

import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ExpenseUnitStateRepository {

    val items: StateFlow<List<ExpenseUnitItem>>

    val itemToShow: Flow<ExpenseUnitItem>

    suspend fun setItemToShow(item: ExpenseUnitItem)

    suspend fun setItems(items: List<ExpenseUnitItem>)
}