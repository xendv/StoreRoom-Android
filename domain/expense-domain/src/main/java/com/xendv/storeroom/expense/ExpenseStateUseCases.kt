package com.xendv.storeroom.expense

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.expense.data.entities.ExpenseItem
import com.xendv.storeroom.expense.data.repositories.ExpenseStateRepository

class ExpenseStateUseCases(
    private val expensesStateRepository: ExpenseStateRepository,
    private val navigator: Navigator,
) {
    val currentExpenseItem = expensesStateRepository.itemToShow

    suspend fun showExpensePage(
        expenseItem: ExpenseItem,
    ) {
        expensesStateRepository.setItemToShow(item = expenseItem)
        navigator.navigate("expense/edit")
    }
}
