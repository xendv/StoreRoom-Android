pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
rootProject.name = "StoreRoom"
include(":app")
include(":ui")
include(":data")
include(":data:products-data")
include(":feature")
include(":feature:products-feature")

include(":domain")
include(":domain:products-domain")
include(":navigation-common")
include(":data-common")
