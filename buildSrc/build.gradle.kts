plugins {
    //id("java-library")
    //id("org.jetbrains.kotlin.jvm")
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}