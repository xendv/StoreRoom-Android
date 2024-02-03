package com.xendv.storeroom.products_unit.ui.productsPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.products_unit.ui.items.ProductUnitItem
import com.xendv.storeroom.products_unit.ui.items.ProductUnitItemPlaceholder

@Composable
fun ProductUnitPage(viewModel: ProductUnitViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val items by viewModel.productUnits.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (items.isEmpty()) {
                repeat(times = 4) {
                    ProductUnitItemPlaceholder()
                }
            } else {
                items.forEach { item ->
                    ProductUnitItem(
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
