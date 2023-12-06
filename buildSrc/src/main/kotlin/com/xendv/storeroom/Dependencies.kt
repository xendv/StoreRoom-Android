package com.xendv.storeroom

object Dependencies {
    object Versions {
        const val lifecycle = "2.6.1"
        const val ktx = "1.8.0"
        const val ktxSerializationJson = "1.4.1"
        const val coroutines = "1.6.4"
        const val compose = "1.4.3"
        const val composeActivity = "1.7.0"
        const val composeMaterial = "1.1.1"
        const val composeCompiler = "1.4.4"
        const val composeHtml = "1.0.2"
        const val coil = "2.3.0"
        const val accompanist = "0.30.0"
        const val navigation = "2.5.3"
        const val paging = "3.2.0-rc01"

        const val koinAndroid = "3.4.0"
        const val koinCompose = "3.4.3"
    }

    object Compose {
        // Compose
        const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
        const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val composeAnimation = "androidx.compose.animation:animation:${Versions.compose}"
        const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
        const val navigationCommon = "androidx.navigation:navigation-common:${Versions.navigation}"
        const val composePaging = "androidx.paging:paging-compose:${Versions.paging}"
        const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
        const val composeHtml = "com.github.ireward:compose-html:${Versions.composeHtml}"
    }
}