package com.xendv.storeroom.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.ui.R
import com.xendv.storeroom.ui.theme.StoreRoomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowContentCard(
    modifier: Modifier = Modifier,
    shape: Shape =  MaterialTheme.shapes.medium,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = shape,
        onClick = onClick ?: {},
        enabled = onClick != null,
    ) {
        Box {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight(),
                content = content,
            )
        }
    }
}

val BottomSurfaceCardShape = RoundedCornerShape(
    bottomStart = 30.dp,
    bottomEnd = 30.dp,
)

val FullSurfaceCardShape = RoundedCornerShape(
    size = 30.dp,
)

val TopSurfaceCardShape = RoundedCornerShape(
    topStart = 30.dp,
    topEnd = 30.dp,
)

@Composable
fun SurfaceCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = FullSurfaceCardShape,
    content: @Composable () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = shape,
        content = content,
    )
}

@Preview
@Composable
private fun RowContentPreview() {
    StoreRoomTheme {
        RowContentCard(
            modifier = Modifier.height(150.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 18.dp, end = 18.dp, bottom = 30.dp),
                text = "Text",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}