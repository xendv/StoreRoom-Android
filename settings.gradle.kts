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

include(":data:products-data")
include(":data:products-unit-data")
include(":data:expense-data")
include(":data:expense-unit-data")
include(":data:lot-data")

include(":domain:products-domain")
include(":domain:products-unit-domain")
include(":domain:expense-domain")
include(":domain:expense-unit-domain")
include(":domain:lot-domain")

include(":feature:products-feature")
include(":feature:products-unit-feature")
include(":feature:expense-feature")
include(":feature:expense-unit-feature")
include(":feature:lot-feature")

include(":navigation-common")
include(":data-common")
