package com.xendv.storeroom.products_unit.ui.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import com.xendv.storeroom.products_unit.ui.productsPage.EditAction
import com.xendv.storeroom.ui.R
import com.xendv.storeroom.ui.cards.CrudContentCard
import com.xendv.storeroom.ui.colors.gray

@Composable
fun ProductUnitItem(
    modifier: Modifier = Modifier,
    item: ProductUnitItem,
    onAction: (EditAction) -> Unit = {},
) {
    CrudContentCard(
        modifier = modifier,
        header = item.id.orEmpty(),
        additionalText = "${item.lot}\n${item.sku}",
        content = {
            IconButton(
                onClick = {
                    onAction(EditAction.Edit(item))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    tint = gray,
                    contentDescription = "Изменить"
                )
            }
            IconButton(
                onClick = {
                    onAction(EditAction.Delete(item))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    tint = Color.Red,
                    contentDescription = "Удалить"
                )
            }
        }
    )
}

@Composable
fun ProductUnitItemPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 26.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
                .placeholder(
                    visible = true,
                    shape = MaterialTheme.shapes.medium,
                    highlight = PlaceholderHighlight.fade(),
                )
        )
    }
}