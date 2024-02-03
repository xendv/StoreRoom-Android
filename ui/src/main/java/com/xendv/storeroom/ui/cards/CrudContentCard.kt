package com.xendv.storeroom.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.ui.R
import com.xendv.storeroom.ui.colors.lightGray
import com.xendv.storeroom.ui.colors.paleGray

@Composable
fun CrudContentCard(
    modifier: Modifier = Modifier,
    header: String,
    additionalText: String,
    content: @Composable RowScope.() -> Unit = {},
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = paleGray
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 17.dp, start = 17.dp),
                    text = header,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 17.dp, bottom = 17.dp),
                    text = additionalText,
                    style = MaterialTheme.typography.labelSmall,
                    color = lightGray
                )
            }
            Row(
                modifier = Modifier
                    .padding(end = 17.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun CrudContentCardPreview(
) {
    CrudContentCard(header = "header", additionalText = "additionalText") {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Изменить"
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Удалить"
            )
        }
    }
}