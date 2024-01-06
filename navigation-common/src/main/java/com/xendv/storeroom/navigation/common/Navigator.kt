package com.xendv.storeroom.navigation.common

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.SharedFlow

interface Navigator {

    val events: SharedFlow<NavigationEvent>

    suspend fun navigate(route: String)

    suspend fun navigate(route: String, navOptions: NavOptionsBuilder.() -> Unit)

    suspend fun popBack(
        route: String? = null,
        saveState: Boolean = false,
        inclusive: Boolean = false,
    )
}