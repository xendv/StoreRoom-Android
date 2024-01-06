package com.xendv.storeroom.products.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xendv.storeroom.products.ui.productsPage.ProductsPage
import com.xendv.storeroom.products.ui.productsPage.ProductsViewModel
import com.xendv.storeroom.ui.tabs.Tab
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
fun NavGraphBuilder.productsGraph() {
    /*        composable(
                route = "newsDetail",
                enterTransition = { navigationSlideInto },
                exitTransition = { navigationSlideOut },
            ) {
                val detailedNewsViewModel = koinViewModel<DetailedNewsViewModel>()
                DetailedNewsPage(viewModel = detailedNewsViewModel)
            }*/
    composable(
        route = "products",
    ) {
        ProductsPage(
            viewModel = koinViewModel()
        )
    }
}

@Composable
fun createMainTabs(
    page: @Composable (ProductsViewModel) -> Unit,
): List<Tab> {
    return listOf(
        Tab(
            title = "Products"
        ) {
            val viewModel = koinViewModel<ProductsViewModel>(
                key = "Products"
            ) {
                parametersOf("Products")
            }
            page(viewModel)
        },
    )
}