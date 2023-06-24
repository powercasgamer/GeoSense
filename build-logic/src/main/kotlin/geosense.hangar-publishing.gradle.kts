import io.papermc.hangarpublishplugin.model.Platforms

plugins {
    id("geosense.common-conventions")
    id("io.papermc.hangar-publish-plugin")
}

hangarPublish {
    publications.register("plugin") {
//        this.apiEndpoint.set("https://hangar.papermc.dev/api/v1/")
        version.set(project.version as String)
        namespace("powercas_gamer", "Geosense")
        channel.set(if (rootProject.versionString().endsWith("-SNAPSHOT")) "Beta" else "Release")
        platforms {
            register(Platforms.PAPER) {
                jar.set(tasks.shadowJar.flatMap { it.archiveFile })
                platformVersions.set(listOf("1.20", "1.20.1"))
                this.dependencies {
                    hangar("MiniPlaceholders", "MiniPlaceholders") {
                        required.set(false)
                    }
                }
            }
        }
        pages {
            resourcePage(provider { file("README.md").readText() })
        }
    }
}
