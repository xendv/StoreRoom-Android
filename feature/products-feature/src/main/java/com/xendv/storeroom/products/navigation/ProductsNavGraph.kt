package com.xendv.storeroom.products.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xendv.storeroom.products.ui.productsPage.EditAction
import com.xendv.storeroom.products.ui.productsPage.ProductsPage
import com.xendv.storeroom.products.ui.productsPage.ProductsViewModel
import com.xendv.storeroom.ui.pages.editPage.EditPage
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
fun NavGraphBuilder.productsGraph() {
    composable(
        route = "products/edit",
    ) {
        val viewModel = koinViewModel<ProductsViewModel>()
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