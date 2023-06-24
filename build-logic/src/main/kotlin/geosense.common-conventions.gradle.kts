import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    base
    id("com.github.johnrengelman.shadow")
    id("net.kyori.indra")
    id("net.kyori.indra.git")
    id("net.kyori.indra.publishing")
    id("net.deltapvp.titan")
    id("xyz.jpenilla.run-paper")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
    gpl3OnlyLicense()
    github(providers.gradleProperty("githubOrg").get(), providers.gradleProperty("githubRepo").get())

    javaVersions {
        minimumToolchain(17)
        target(17)
    }
}

tasks {
    runServer<RunServer> {
        minecraftVersion("1.20.1")
        runDirectory(rootProject.file("run"))
        javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
    }

    shadowJar<ShadowJar> {
        isEnableRelocation = true
        relocationPrefix = "net.deltapvp.geosense.libs"
    }
}
