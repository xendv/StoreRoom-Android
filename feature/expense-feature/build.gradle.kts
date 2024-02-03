import com.xendv.storeroom.StoreroomDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

android {
    namespace = "com.xendv.storeroom.expense"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}

dependencies {
    implementation(project(":ui"))
    api(project(":domain:expense-domain"))
    implementation(project(":navigation-common"))
    implementation(StoreroomDependencies.Android.coreKtx)
    implementation(StoreroomDependencies.Android.lifecycleRuntime)
    implementation(StoreroomDependencies.Compose.composeActivity)
    implementation(StoreroomDependencies.Compose.composeUi)
    implementation(StoreroomDependencies.Compose.composePreview)
    implementation(StoreroomDependencies.Compose.composeMaterial3)
    implementation(StoreroomDependencies.Android.placeholder)
    implementation(StoreroomDependencies.Android.placeholderMaterial)
    implementation(StoreroomDependencies.Compose.composePaging)
    implementation(StoreroomDependencies.Compose.coil)
    implementation(StoreroomDependencies.Android.navigationAnimation)


    implementation(StoreroomDependencies.Android.koinCompose)
    implementation(StoreroomDependencies.Compose.composePaging)
    implementation(StoreroomDependencies.Android.retrofit)
    implementation("androidx.navigation:navigation-common-ktx:2.5.2")
    implementation(StoreroomDependencies.Android.navigationAnimation)
    implementation(project(":data:expense-data"))
    implementation(project(":domain:expense-domain"))

    debugImplementation(StoreroomDependencies.Compose.composeTooling)

}