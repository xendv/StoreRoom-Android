package com.xendv.storeroom.expense_unit

import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitRepository
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitStateRepository

class ExpenseUnitUseCases(
    private val expenseUnitsRepository: ExpenseUnitRepository,
    private val expenseUnitStateRepository: ExpenseUnitStateRepository,
) {

    val items = expenseUnitStateRepository.items

    suspend fun fetchItems() {
        val expenseUnits = expenseUnitsRepository.fetchItems()
        expenseUnitStateRepository.setItems(expenseUnits)
    }

    suspend fun deleteItem(item: ExpenseUnitItem): Boolean {
        return expenseUnitsRepository.deleteItem(item)
    }

    suspend fun updateItem(item: ExpenseUnitItem): ExpenseUnitItem {
        return expenseUnitsRepository.updateItem(item)
    }

    suspend fun createItem(item: ExpenseUnitItem): ExpenseUnitItem {
        return expenseUnitsRepository.createItem(item)
    }
}