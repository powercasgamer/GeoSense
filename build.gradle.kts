import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.papermc.hangarpublishplugin.model.Platforms
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("geosense.common-conventions")
    id("geosense.hangar-publishing")
    id("geosense.modrinth-publishing")
    id("geosense.indra-publishing")
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

tasks {
    named("ideaModule") {
        notCompatibleWithConfigurationCache("https://github.com/gradle/gradle/issues/13480")
    }
}
