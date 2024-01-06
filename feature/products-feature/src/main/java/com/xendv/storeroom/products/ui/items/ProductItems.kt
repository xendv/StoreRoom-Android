package com.xendv.storeroom.products.ui.items

/*import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder*/
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.xendv.storeroom.products.data.entities.ProductItem
import com.xendv.storeroom.ui.colors.lightGray

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    item: ProductItem,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 26.dp, vertical = 19.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            item.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 17.dp),
                text = item.barcode + " // " + item.sku,
                style = MaterialTheme.typography.labelSmall,
                color = lightGray
            )
        }
    }
}


@Composable
fun ProductItemPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 26.dp, vertical = 19.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {

                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(
                            visible = true,
                            shape = MaterialTheme.shapes.medium,
                            highlight = PlaceholderHighlight.fade(),
                        ),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )

            Text(
                modifier = Modifier
                    .padding(top = 17.dp),
                text = "",
                style = MaterialTheme.typography.labelSmall,
                color = lightGray
            )
        }
    }
}