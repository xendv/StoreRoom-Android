import com.xendv.storeroom.StoreroomDependencies
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    kotlin("plugin.serialization")
}
android {
    namespace = "com.xendv.storeroom.expense.data"
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
    implementation(StoreroomDependencies.Android.ktxSerializationJson)
    implementation(StoreroomDependencies.Android.retrofit)
    implementation(StoreroomDependencies.Android.retrofitConverterKtx)
    implementation(StoreroomDependencies.Android.coroutines)
    api(StoreroomDependencies.Compose.pagingRuntime)
    api(project(":data-common"))
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}