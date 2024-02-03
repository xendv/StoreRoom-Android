package com.xendv.storeroom.expense_unit

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitStateRepository

class ExpenseUnitStateUseCases(
    private val expenseUnitStateRepository: ExpenseUnitStateRepository,
    private val navigator: Navigator,
) {
    val currentExpenseUnitItem = expenseUnitStateRepository.itemToShow

    suspend fun showExpenseUnitPage(
        expenseUnitItem: ExpenseUnitItem,
    ) {
        expenseUnitStateRepository.setItemToShow(item = expenseUnitItem)
        navigator.navigate("expenseUnit/edit")
    }
}
