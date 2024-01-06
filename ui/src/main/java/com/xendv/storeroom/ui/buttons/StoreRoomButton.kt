package com.xendv.storeroom.ui.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.ui.colors.cerulean
import com.xendv.storeroom.ui.colors.white

@Composable
fun StoreRoomButton(
    modifier: Modifier = Modifier,
    color: Color = cerulean.copy(alpha = 0.5f),
    textColor: Color = white,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontWeight: FontWeight = FontWeight.SemiBold,
    title: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .heightIn(min = 50.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = textColor
        ),
        onClick = onClick,
    ) {
        Text(
            text = title,
            style = textStyle,
            fontWeight = fontWeight,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun StoreRoomButtonPreview() {
    Column {
        StoreRoomButton(title = "Im button")
    }
}