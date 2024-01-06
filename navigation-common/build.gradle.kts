import com.xendv.storeroom.StoreroomDependencies

plugins {
    //id("java-library")
    id("com.android.library")
    //id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.stmegi.app.navigation"
    compileSdk = 33

    defaultConfig {
        minSdk = 22
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(StoreroomDependencies.Android.coreKtx)
    implementation(StoreroomDependencies.Compose.navigationCommon)
    implementation(StoreroomDependencies.Android.coroutines)
}


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
