package com.xendv.storeroom.ui.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.ui.theme.StoreRoomTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    @DrawableRes navigationIcon: Int? = null,
    navigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val scope = rememberCoroutineScope()
    val navigator = koinInject<Navigator>()

    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = {
                        navigationIconClick?.invoke() ?: run {
                            scope.launch {
                                navigator.popBack()
                            }
                        }
                    }
                ) {
                    Icon(
                        painterResource(id = navigationIcon),
                        contentDescription = "toolbar icon",
                    )
                }
            }
        },
        title = {
            title?.let {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ToolbarPreview() {
    StoreRoomTheme {
        Toolbar(
            title = "Charitable Foundation"
        )
    }
}