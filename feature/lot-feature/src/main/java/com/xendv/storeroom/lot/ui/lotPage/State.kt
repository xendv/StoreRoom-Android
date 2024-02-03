package com.xendv.storeroom.lot.ui.lotPage

import com.xendv.storeroom.lot.data.entities.LotItem
import com.xendv.storeroom.ui.pages.editPage.Editable

sealed interface EditAction {

    data class Delete(val item: LotItem) : EditAction

    data class Edit(val item: LotItem) : EditAction

    data class Update(val editables: List<Editable>) : EditAction
}