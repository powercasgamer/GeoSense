rootProject.name = "geosense-build-logic"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://new-repo.deltapvp.net/releases") {
            name = "deltaReleases"
            mavenContent { releasesOnly() }
        }
        maven(url = "https://new-repo.deltapvp.net/snapshots") {
            name = "deltaSnapshots"
            mavenContent { snapshotsOnly() }
        }
    }

    versionCatalogs {
        register("libs") {
            from(files("../gradle/libs.versions.toml")) // include from parent project
        }
    }
}