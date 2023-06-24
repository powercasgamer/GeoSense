import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.papermc.hangarpublishplugin.model.Platforms
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("geosense.common-conventions")
    alias(libs.plugins.indra)
    alias(libs.plugins.indra.publishing)
    alias(libs.plugins.titan)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.hangar.publish)
}

repositories {
    mavenCentral()
    maven("https://maven.deltapvp.net")
}

dependencies {
    compileOnly(libs.paper)
    implementation(libs.bstats)
    compileOnly(libs.miniplaceholders.api)
}

indra {
    gpl3OnlyLicense()
    javaVersions {
        minimumToolchain(17)
        target(17)
    }

    publishReleasesTo("drink", "https://new-repo.deltapvp.net/releases/")
    publishSnapshotsTo("drink", "https://new-repo.deltapvp.net/snapshots/")

    configurePublications {
        this.artifactId = project.name.lowercase()
        pom {
            developers {
                developer {
                    id.set("powercas_gamer")
                    name.set("Cas")
                    timezone.set("Europe/Amsterdam")
                    email.set("cas[at]deltapvp[dot]net")
                    organization.set("DeltaPvP")
                }
            }
        }
    }
}

hangarPublish {
    publications.register("testing") {
        this.apiEndpoint.set("https://hangar.papermc.dev/api/v1/")
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

tasks {
    runServer<RunServer> {
        minecraftVersion("1.20.1")
    }

    shadowJar<ShadowJar> {
        isEnableRelocation = true
        relocationPrefix = "net.deltapvp.geosense.libs"
    }

    named("ideaModule") {
        notCompatibleWithConfigurationCache("https://github.com/gradle/gradle/issues/13480")
    }
}
