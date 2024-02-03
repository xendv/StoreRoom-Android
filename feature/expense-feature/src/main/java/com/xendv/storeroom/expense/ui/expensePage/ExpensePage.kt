package com.xendv.storeroom.expense.ui.expensePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.expense.ui.items.ExpenseItem
import com.xendv.storeroom.expense.ui.items.ExpenseItemPlaceholder

@Composable
fun ExpensePage(viewModel: ExpenseViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val items by viewModel.expense.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (items.isEmpty()) {
                repeat(times = 4) {
                    ExpenseItemPlaceholder()
                }
            } else {
                items.forEach { item ->
                    ExpenseItem(
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
