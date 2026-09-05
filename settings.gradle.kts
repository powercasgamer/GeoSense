pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://new-repo.deltapvp.net/releases/") {
            mavenContent {
                releasesOnly()
            }
        }
    }
}

includeBuild("build-logic")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "GeoSense"
