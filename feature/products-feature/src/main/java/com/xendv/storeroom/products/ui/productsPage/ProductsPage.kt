package com.xendv.storeroom.products.ui.productsPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.products.ui.items.ProductItem
import com.xendv.storeroom.products.ui.items.ProductItemPlaceholder
import com.xendv.storeroom.ui.colors.black

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
                    if (it != 3) {
                        Divider(
                            modifier = Modifier
                                .padding(start = 127.dp),
                            color = black,
                            thickness = 1.dp
                        )
                    }
                }
            } else {
                items.forEachIndexed { index, item ->
                    ProductItem(
                        modifier = Modifier.clickable {
                            viewModel.onProductItemClicked(item, index)
                        },
                        item = item,
                    )
                    if (index < items.size - 1) {
                        Divider(
                            modifier = Modifier
                                .padding(start = 127.dp),
                            color = black,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}
