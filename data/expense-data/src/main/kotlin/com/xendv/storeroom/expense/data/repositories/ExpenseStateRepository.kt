package com.xendv.storeroom.expense.data.repositories

import com.xendv.storeroom.expense.data.entities.ExpenseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ExpenseStateRepository {

    val items: StateFlow<List<ExpenseItem>>

    val itemToShow: Flow<ExpenseItem>

    suspend fun setItemToShow(item: ExpenseItem)

    suspend fun setItems(items: List<ExpenseItem>)
}