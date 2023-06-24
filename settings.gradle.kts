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
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "GeoSense"
