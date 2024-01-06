package com.xendv.storeroom.ui.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T: Any> PagedLazyColumn(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    loadingPreviewCount: Int = 5,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    previewOnError: Boolean = true,
    content: LazyListScope.() -> Unit = {},
    itemContent: @Composable LazyItemScope.(index: Int, item: T?) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        content()
        when (items.loadState.refresh) {
            is LoadState.Error -> {
                if (previewOnError) {
                    items(count = loadingPreviewCount) {
                        itemContent(it, null)
                    }
                }
            }
            is LoadState.Loading -> {
                items(count = loadingPreviewCount) {
                    itemContent(it, null)
                }
            }
            else -> Unit
        }
        items(count = items.itemCount) { index ->
            val item = items[index]
            item?.let {
                itemContent(index, item)
            }
        }
        when (items.loadState.append) {
            is LoadState.Error -> {
                if (previewOnError) {
                    items(count = loadingPreviewCount) {
                        itemContent(it, null)
                    }
                }
            }
            is LoadState.Loading -> {
                items(count = loadingPreviewCount) {
                    itemContent(it, null)
                }
            }
            else -> Unit
        }
    }
}