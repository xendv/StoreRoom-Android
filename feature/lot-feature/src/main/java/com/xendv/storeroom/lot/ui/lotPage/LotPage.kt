package com.xendv.storeroom.lot.ui.lotPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.lot.ui.items.LotItem
import com.xendv.storeroom.lot.ui.items.LotItemPlaceholder

@Composable
fun LotPage(viewModel: LotViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val items by viewModel.lot.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (items.isEmpty()) {
                repeat(times = 4) {
                    LotItemPlaceholder()
                }
            } else {
                items.forEach { item ->
                    LotItem(
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
