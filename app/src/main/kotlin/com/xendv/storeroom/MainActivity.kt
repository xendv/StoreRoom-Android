package com.xendv.storeroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.navigation.subscribeOnNavigationEvents
import com.xendv.storeroom.products.navigation.productsGraph
import com.xendv.storeroom.ui.theme.StoreRoomTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator = get<Navigator>()
            val navController = rememberAnimatedNavController()
            subscribeOnNavigationEvents(navController, navigator)

            StoreRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "products",//"home",
                    ) {
                        productsGraph()
                    }
                }
            }
        }
    }
}
