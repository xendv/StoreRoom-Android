package com.xendv.storeroom.expense_unit.ui.expenseUnitPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.expense_unit.ui.items.ExpenseUnitItem
import com.xendv.storeroom.expense_unit.ui.items.ExpenseUnitItemPlaceholder

@Composable
fun ExpenseUnitPage(viewModel: ExpenseUnitViewModel) {
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
                    ExpenseUnitItemPlaceholder()
                }
            } else {
                items.forEach { item ->
                    ExpenseUnitItem(
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
