package com.xendv.storeroom.navigation.common

import androidx.navigation.NavOptionsBuilder

sealed class NavigationEvent {

    data class Navigate(
        val route: String,
        val options: NavOptionsBuilder.() -> Unit = {},
    ) : NavigationEvent()

    data class PopBack(
        val route: String?,
        val saveState: Boolean,
        val inclusive: Boolean,
    ) : NavigationEvent()
}

