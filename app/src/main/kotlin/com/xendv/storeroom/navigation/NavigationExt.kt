package com.xendv.storeroom.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.xendv.storeroom.navigation.common.NavigationEvent
import com.xendv.storeroom.navigation.common.Navigator
import kotlinx.coroutines.launch

fun LifecycleOwner.subscribeOnNavigationEvents(
    navController: NavController,
    navigator: Navigator,
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            navigator.events.collect { event ->
                when (event) {
                    is NavigationEvent.Navigate -> {
                        navController.navigate(event.route, event.options)
                    }
                    is NavigationEvent.PopBack -> {
                        event.route?.let { route ->
                            navController.popBackStack(
                                route = route,
                                inclusive = event.inclusive,
                                saveState = event.saveState,
                            )
                        } ?: run {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}