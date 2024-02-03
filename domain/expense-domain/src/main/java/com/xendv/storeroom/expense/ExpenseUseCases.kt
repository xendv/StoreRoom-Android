package com.xendv.storeroom.expense

import com.xendv.storeroom.expense.data.entities.ExpenseItem
import com.xendv.storeroom.expense.data.repositories.ExpenseRepository
import com.xendv.storeroom.expense.data.repositories.ExpenseStateRepository

class ExpenseUseCases(
    private val expenseRepository: ExpenseRepository,
    private val expenseStateRepository: ExpenseStateRepository,
) {

    val items = expenseStateRepository.items

    suspend fun fetchItems() {
        val expenses = expenseRepository.fetchItems()
        expenseStateRepository.setItems(expenses)
    }

    suspend fun deleteItem(item: ExpenseItem): Boolean {
        return expenseRepository.deleteItem(item)
    }

    suspend fun updateItem(item: ExpenseItem): ExpenseItem {
        return expenseRepository.updateItem(item)
    }

    suspend fun createItem(item: ExpenseItem): ExpenseItem {
        return expenseRepository.createItem(item)
    }
}