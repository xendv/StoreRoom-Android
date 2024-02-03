package com.xendv.storeroom.expense.ui.expensePage

import com.xendv.storeroom.expense.data.entities.ExpenseItem
import com.xendv.storeroom.ui.pages.editPage.Editable

sealed interface EditAction {

    data class Delete(val item: ExpenseItem) : EditAction

    data class Edit(val item: ExpenseItem) : EditAction

    data class Update(val editables: List<Editable>) : EditAction
}