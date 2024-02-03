package com.xendv.storeroom.products.ui.productsPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.products.ui.items.ProductItem
import com.xendv.storeroom.products.ui.items.ProductItemPlaceholder

@Composable
fun ProductsPage(viewModel: ProductsViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val items by viewModel.products.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (items.isEmpty()) {
                repeat(times = 4) {
                    ProductItemPlaceholder()
                }
            } else {
                items.forEach { item ->
                    ProductItem(
                        modifier = Modifier
                            .padding(horizontal = 26.dp, vertical = 8.dp),
                        item = item,
                        onAction = viewModel::processEditAction,
                    )
                }
            }
        }
    }
}
