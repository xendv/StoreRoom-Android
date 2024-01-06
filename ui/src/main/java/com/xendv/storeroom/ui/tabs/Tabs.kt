package com.xendv.storeroom.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.xendv.storeroom.ui.colors.black
import com.xendv.storeroom.ui.colors.lightGray
import com.xendv.storeroom.ui.theme.StoreRoomTheme

@Composable
fun StoreRoomTabs(
    modifier: Modifier = Modifier,
    titles: List<String> = emptyList(),
    initialIndex: Int = 0,
    tabColors: TabColors = TabColors.default(),
    onTabClick: (Int) -> Boolean = { true },
) {

    var selectedIndex by rememberSaveable(initialIndex) {
        mutableStateOf(initialIndex)
    }
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex,
        containerColor = tabColors.containerColor,
        contentColor = tabColors.contentColor,
        edgePadding = 26.dp,
        divider = {},
        indicator = {
            TabIndicator(
                tabPosition = it[selectedIndex],
                indicatorColor = tabColors.indicatorColor,
            )
        },
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .wrapContentWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        if (onTabClick(index)) {
                            selectedIndex = index
                        }
                    },
                title = title,
                selected = selectedIndex == index,
                tabColors = tabColors,
            )
        }
    }
}

@Composable
private fun Tab(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    tabColors: TabColors = TabColors.default(),
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .align(Center),
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) tabColors.activeTextColor else tabColors.normalTextColor,
            maxLines = 1,
        )
    }
}


@Composable
private fun TabIndicator(
    modifier: Modifier = Modifier,
    tabPosition: TabPosition,
    indicatorColor: Color,
) {
    Box(
        modifier = modifier
            .zIndex(-1f)
            .tabIndicatorOffset(tabPosition)
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .align(Center)
                .clip(CircleShape)
                .background(color = indicatorColor)
        )
    }
}

@Stable
data class TabColors(
    val indicatorColor: Color,
    val normalTextColor: Color,
    val activeTextColor: Color = normalTextColor,
    val containerColor: Color,
    val contentColor: Color,
) {
    companion object {
        @Composable
        fun default(): TabColors = TabColors(
            indicatorColor = lightGray,
            normalTextColor = black,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun TabsPreview() {
    StoreRoomTheme {
        StoreRoomTabs(
            modifier = Modifier
                .fillMaxWidth(),
            initialIndex = 1,
            titles = listOf("News", "About Us", "President Dgydfdgddgh", "Contacts")
        )
    }
}