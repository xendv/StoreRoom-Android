package com.xendv.storeroom.expense.data.repositories

import com.xendv.storeroom.expense.data.api.ExpenseApi
import com.xendv.storeroom.expense.data.entities.ExpenseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseRepositoryImpl(
    private val expenseApi: ExpenseApi,
): ExpenseRepository {

    override suspend fun fetchItems(): List<ExpenseItem> = withContext(Dispatchers.IO) {
        expenseApi.getExpense()
    }

    override suspend fun getItem(id: String): ExpenseItem = withContext(Dispatchers.IO) {
        expenseApi.getExpense(id)
    }

    override suspend fun deleteItem(item: ExpenseItem): Boolean = withContext(Dispatchers.IO) {
        requireNotNull(item.id)
        expenseApi.deleteExpense(id = item.id)
    }

    override suspend fun updateItem(item: ExpenseItem): ExpenseItem = withContext(Dispatchers.IO) {
        expenseApi.updateExpense(item)
    }

    override suspend fun createItem(item: ExpenseItem): ExpenseItem = withContext(Dispatchers.IO) {
        expenseApi.createExpense(item)
    }
}