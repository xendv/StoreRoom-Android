import com.xendv.storeroom.StoreroomDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
}

android {
    namespace = "com.xendv.storeroom.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}

dependencies {
    implementation(StoreroomDependencies.Android.coreKtx)
    implementation(StoreroomDependencies.Android.zxingWrapper)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(project(":navigation-common"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(StoreroomDependencies.Compose.composeActivity)
    implementation(StoreroomDependencies.Compose.composeAnimation)
    implementation(StoreroomDependencies.Compose.composeUi)
    implementation(StoreroomDependencies.Compose.composePreview)
    implementation(StoreroomDependencies.Compose.composeMaterial3)
    implementation(StoreroomDependencies.Compose.coil)
    implementation(StoreroomDependencies.Android.permissions)
    implementation("io.insert-koin:koin-androidx-compose:3.4.3")
    api(StoreroomDependencies.Compose.composeHtml)
    api(StoreroomDependencies.Compose.composePaging)
    debugImplementation(StoreroomDependencies.Compose.composeTooling)
}