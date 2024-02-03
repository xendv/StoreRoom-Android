package com.xendv.storeroom

object StoreroomDependencies {
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
        const val datastore = "1.0.0"

        const val retrofit = "2.9.0"
        const val retrofitConverterKtx = "0.8.0"
        const val okHttp = "4.10.0"

        const val koinAndroid = "3.4.0"
        const val koinCompose = "3.4.3"

        const val zxingWrapper = "4.3.0"
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

    object Android {
        // AndroidX
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

        // Retrofit
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor ="com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterKtx = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitConverterKtx}"

        const val ktxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.ktxSerializationJson}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        // Koin
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"

        // Accompanist
        const val placeholder = "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"
        const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        const val permissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"

        //ZXing
        const val zxingWrapper = "com.journeyapps:zxing-android-embedded:${Versions.zxingWrapper}"
    }
}