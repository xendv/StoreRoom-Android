import com.xendv.storeroom.StoreroomDependencies
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.xendv.storeroom.products"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":data:products-data"))
    implementation(StoreroomDependencies.Android.coreKtx)
    implementation(StoreroomDependencies.Android.ktxSerializationJson)
    implementation(StoreroomDependencies.Android.retrofit)
    implementation(StoreroomDependencies.Android.retrofitConverterKtx)
    implementation(StoreroomDependencies.Compose.navigationCommon)
    implementation(StoreroomDependencies.Compose.composePaging)
    implementation(StoreroomDependencies.Android.coroutines)
}