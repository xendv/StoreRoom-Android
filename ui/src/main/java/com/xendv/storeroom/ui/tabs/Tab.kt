package com.xendv.storeroom.ui.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class Tab(
    val title: String,
    val content: @Composable (page: Int) -> Unit,
)