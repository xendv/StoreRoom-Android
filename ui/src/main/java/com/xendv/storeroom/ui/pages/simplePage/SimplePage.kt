package com.xendv.storeroom.ui.pages.simplePage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.xendv.storeroom.ui.toolbar.Toolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimplePage(
    modifier: Modifier = Modifier,
    title: String = "",
    toolbar: @Composable (TopAppBarScrollBehavior) -> Unit = { scrollBehavior ->
        Toolbar(
            title = title,
            scrollBehavior = scrollBehavior,
        )
    },
    content: @Composable (PaddingValues) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            toolbar(scrollBehavior)
        },
        content = content,
    )
}