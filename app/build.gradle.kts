import com.xendv.storeroom.StoreroomDependencies.Versions
import com.xendv.storeroom.StoreroomDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.8.10" apply false
}

android {
    namespace = "com.xendv.storeroom"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.xendv.storeroom"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:products-feature"))
    implementation("androidx.core:core-ktx:${Versions.ktx}")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    implementation(project(":ui"))
    implementation("androidx.core:core-ktx:${Versions.ktx}")
    implementation(project(mapOf("path" to ":navigation-common")))
    implementation(project(mapOf("path" to ":data-common")))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(StoreroomDependencies.Android.coreKtx)
    implementation(StoreroomDependencies.Android.lifecycleRuntime)
    implementation(StoreroomDependencies.Compose.composeActivity)
    implementation(StoreroomDependencies.Compose.composeUi)
    implementation(StoreroomDependencies.Compose.composePreview)
    implementation(StoreroomDependencies.Compose.composeMaterial3)



    implementation(StoreroomDependencies.Android.koinAndroid)
    implementation(StoreroomDependencies.Compose.navigation)
    implementation(StoreroomDependencies.Android.navigationAnimation)
    implementation(StoreroomDependencies.Android.systemUiController)
    implementation(StoreroomDependencies.Android.ktxSerializationJson)
    implementation(StoreroomDependencies.Android.retrofit)
    implementation(StoreroomDependencies.Android.retrofitConverterKtx)
    implementation(StoreroomDependencies.Android.loggingInterceptor)
    implementation(StoreroomDependencies.Android.coroutines)
    implementation(StoreroomDependencies.Android.koinCompose)
}