plugins {
    //id("java-library")
    //id("org.jetbrains.kotlin.jvm")
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

repositories {
    mavenCentral()
}