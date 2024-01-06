package com.xendv.storeroom.navigation.common;

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigatorImpl : Navigator {

    private val _events = MutableSharedFlow<NavigationEvent>()
    override val events: SharedFlow<NavigationEvent> = _events.asSharedFlow()

    override suspend fun navigate(route: String) {
        _events.emit(
            NavigationEvent.Navigate(route = route)
        )
    }

    override suspend fun navigate(route: String, navOptions: NavOptionsBuilder.() -> Unit) {
        _events.emit(
            NavigationEvent.Navigate(
                route = route,
                options = navOptions,
            )
        )
    }

    override suspend fun popBack(
        route: String?,
        saveState: Boolean,
        inclusive: Boolean
    ) {
        _events.emit(
            NavigationEvent.PopBack(
                route = route,
                saveState = saveState,
                inclusive = inclusive,
            )
        )
    }
}