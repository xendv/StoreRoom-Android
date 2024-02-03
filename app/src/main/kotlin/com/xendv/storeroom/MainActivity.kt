package com.xendv.storeroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.xendv.storeroom.expense.navigation.expenseGraph
import com.xendv.storeroom.expense.ui.expensePage.ExpensePage
import com.xendv.storeroom.expense.ui.expensePage.ExpenseViewModel
import com.xendv.storeroom.expense_unit.navigation.expenseUnitGraph
import com.xendv.storeroom.expense_unit.ui.expenseUnitPage.ExpenseUnitPage
import com.xendv.storeroom.expense_unit.ui.expenseUnitPage.ExpenseUnitViewModel
import com.xendv.storeroom.lot.navigation.lotGraph
import com.xendv.storeroom.lot.ui.lotPage.LotPage
import com.xendv.storeroom.lot.ui.lotPage.LotViewModel
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.navigation.subscribeOnNavigationEvents
import com.xendv.storeroom.products.navigation.productsGraph
import com.xendv.storeroom.products.ui.productsPage.ProductsPage
import com.xendv.storeroom.products.ui.productsPage.ProductsViewModel
import com.xendv.storeroom.products_unit.navigation.productUnitGraph
import com.xendv.storeroom.products_unit.ui.productsPage.ProductUnitPage
import com.xendv.storeroom.products_unit.ui.productsPage.ProductUnitViewModel
import com.xendv.storeroom.ui.pages.tabbedPage.TabbedPage
import com.xendv.storeroom.ui.tabs.Tab
import com.xendv.storeroom.ui.theme.StoreRoomTheme
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator = get<Navigator>()
            val navController = rememberAnimatedNavController()
            subscribeOnNavigationEvents(navController, navigator)

            StoreRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "main",
                    ) {
                        mainGraph()
                        productsGraph()
                        productUnitGraph()
                        expenseGraph()
                        expenseUnitGraph()
                        lotGraph()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph() {
    composable(
        route = "main",
    ) {
        MainPage()
    }
}


@Composable
fun createMainTabs(
): List<Tab> {
    return listOf(
        Tab(
            title = "Товары"
        ) {
            val viewModel = koinViewModel<ProductsViewModel>()
            ProductsPage(viewModel = viewModel)
        },
        Tab(
            title = "Поставки"
        ) {
            val viewModel = koinViewModel<LotViewModel>()
            LotPage(viewModel = viewModel)
        },
        Tab(
            title = "Товарные единицы"
        ) {
            val viewModel = koinViewModel<ProductUnitViewModel>()
            ProductUnitPage(viewModel = viewModel)
        },
        Tab(
            title = "Отгрузки"
        ) {
            val viewModel = koinViewModel<ExpenseViewModel>()
            ExpensePage(viewModel = viewModel)
        },
        Tab(
            title = "Отгруженные товары"
        ) {
            val viewModel = koinViewModel<ExpenseUnitViewModel>()
            ExpenseUnitPage(viewModel = viewModel)
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    TabbedPage(
        title = "Учет товаров",
        tabs = createMainTabs()
    )
}
