plugins {
    id("geosense.common-conventions")
    id("net.kyori.indra.publishing")
}

indra {
    publishReleasesTo("drink", "https://new-repo.deltapvp.net/releases/")
    publishSnapshotsTo("drink", "https://new-repo.deltapvp.net/snapshots/")

    configurePublications {
        this.artifactId = rootProject.name.lowercase()
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
