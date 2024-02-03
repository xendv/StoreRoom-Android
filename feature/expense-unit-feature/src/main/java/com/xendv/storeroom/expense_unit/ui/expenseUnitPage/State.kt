package com.xendv.storeroom.expense_unit.ui.expenseUnitPage

import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import com.xendv.storeroom.ui.pages.editPage.Editable

sealed interface EditAction {

    data class Delete(val item: ExpenseUnitItem) : EditAction

    data class Edit(val item: ExpenseUnitItem) : EditAction

    data class Update(val editables: List<Editable>) : EditAction
}