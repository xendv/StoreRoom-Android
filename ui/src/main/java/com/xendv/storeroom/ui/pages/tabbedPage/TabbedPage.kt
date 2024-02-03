package com.xendv.storeroom.ui.pages.tabbedPage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.xendv.storeroom.ui.toolbar.Toolbar
import com.xendv.storeroom.ui.tabs.StoreRoomTabs
import com.xendv.storeroom.ui.tabs.Tab
import com.xendv.storeroom.ui.tabs.TabColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TabbedPage(
    modifier: Modifier = Modifier,
    title: String = "",
    tabs: List<Tab>,
    initialIndex: Int = 0,
    indexState: MutableState<Int> = rememberSaveable { mutableStateOf(initialIndex) },
    tabBlockModifier: Modifier = Modifier,
    pagerScrollEnabled: Boolean = false,
    tabColors: TabColors = TabColors.default(),
    toolbar: @Composable (TopAppBarScrollBehavior) -> Unit = { scrollBehavior ->
        Toolbar(
            title = title,
            scrollBehavior = scrollBehavior,
        )
    }
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = initialIndex,
    )
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var index by indexState

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.settledPage }.collect { page ->
            index = page
        }
    }

    LaunchedEffect(index) {
        if (index != pagerState.targetPage) {
            pagerState.animateScrollToPage(page = index)
        }
    }

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column(
                modifier = tabBlockModifier
                    .background(
                        color = tabColors.containerColor,
                    ),
            ) {
                toolbar(scrollBehavior)
                StoreRoomTabs(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    titles = tabs.map { it.title },
                    onTabClick = {
                        index = it
                        scope.launch {
                            pagerState.animateScrollToPage(page = it)
                        }
                    },
                    tabColors = tabColors,
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState,
                userScrollEnabled = pagerScrollEnabled,
                pageCount = tabs.size,
            ) { page -> tabs[page].content(page) }
        }
    }
}