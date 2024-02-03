package com.xendv.storeroom.expense_unit.data.repositories

import com.xendv.storeroom.expense_unit.data.api.ExpenseUnitApi
import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseUnitRepositoryImpl(
    private val expenseUnitApi: ExpenseUnitApi,
): ExpenseUnitRepository {

    override suspend fun fetchItems(): List<ExpenseUnitItem> = withContext(Dispatchers.IO) {
        expenseUnitApi.getExpenseUnits()
    }

    override suspend fun getItem(id: String): ExpenseUnitItem = withContext(Dispatchers.IO) {
        expenseUnitApi.getExpenseUnit(id)
    }

    override suspend fun deleteItem(item: ExpenseUnitItem): Boolean = withContext(Dispatchers.IO) {
        requireNotNull(item.id)
        expenseUnitApi.deleteExpenseUnit(id = item.id)
    }

    override suspend fun updateItem(item: ExpenseUnitItem): ExpenseUnitItem = withContext(Dispatchers.IO) {
        expenseUnitApi.updateExpenseUnit(item)
    }

    override suspend fun createItem(item: ExpenseUnitItem): ExpenseUnitItem = withContext(Dispatchers.IO) {
        expenseUnitApi.createExpenseUnit(item)
    }
}