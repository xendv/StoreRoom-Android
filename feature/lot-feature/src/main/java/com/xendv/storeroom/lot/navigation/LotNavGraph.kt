package com.xendv.storeroom.lot.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xendv.storeroom.lot.ui.lotPage.EditAction
import com.xendv.storeroom.lot.ui.lotPage.LotViewModel
import com.xendv.storeroom.ui.pages.editPage.EditPage
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
fun NavGraphBuilder.lotGraph() {
    composable(
        route = "lot/edit",
    ) {
        val viewModel = koinViewModel<LotViewModel>()
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