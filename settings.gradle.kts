pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "TaskTreckerVIceMeat"
include(":app")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:designsystem")
include(":feature")
include(":feature:profile")
include(":feature:hubs")
include(":feature:auth")
include(":feature:loading")
include(":feature:feature-common")
include(":core:common")
include(":feature:media-container")

