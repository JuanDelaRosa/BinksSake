pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AkibaRoom"
include(":app")
include(":core:ui")
include(":core:utils")
include(":core:database")
include(":core:network")
include(":core:datastore")
include(":core:firebase")
include(":features:auth")
include(":features:auth-api")
include(":features:profile")
include(":features:profile-api")
include(":features:social")
include(":features:social-api")
include(":features:collection")
include(":features:collection-api")
include(":features:store")
include(":features:store-api")
include(":features:figures")
include(":features:figures-api")
include(":core:domain")
include(":core-firebase")
