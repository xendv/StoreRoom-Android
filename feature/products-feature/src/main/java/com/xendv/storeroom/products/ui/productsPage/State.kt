package com.xendv.storeroom.products.ui.productsPage

import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.ui.pages.editPage.Editable

sealed interface EditAction {

    data class Delete(val item: ProductItem) : EditAction

    data class Edit(val item: ProductItem) : EditAction

    data class Update(val editables: List<Editable>) : EditAction
}