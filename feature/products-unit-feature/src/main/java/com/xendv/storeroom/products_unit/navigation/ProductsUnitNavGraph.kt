package com.xendv.storeroom.products_unit.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xendv.storeroom.products_unit.ui.productsPage.EditAction
import com.xendv.storeroom.products_unit.ui.productsPage.ProductUnitViewModel
import com.xendv.storeroom.ui.pages.editPage.EditPage
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
fun NavGraphBuilder.productUnitGraph() {
    composable(
        route = "productUnit/edit",
    ) {
        val viewModel = koinViewModel<ProductUnitViewModel>()
        val editables by viewModel.editables.collectAsState(initial = emptyList())

        EditPage(
            title = "Edit",
            editables = editables,
            onSaveClicked = {
                viewModel.processEditAction(EditAction.Update(editables))
            }
        )
    }
}