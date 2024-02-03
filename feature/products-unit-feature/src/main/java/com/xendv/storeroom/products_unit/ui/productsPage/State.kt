package com.xendv.storeroom.products_unit.ui.productsPage

import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import com.xendv.storeroom.ui.pages.editPage.Editable

sealed interface EditAction {

    data class Delete(val item: ProductUnitItem) : EditAction

    data class Edit(val item: ProductUnitItem) : EditAction

    data class Update(val editables: List<Editable>) : EditAction
}