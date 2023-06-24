plugins {
    id("geosense.common-conventions")
    id("com.modrinth.minotaur")
}

modrinth {
    token.set(providers.gradleProperty("modrinth-api-key"))
    projectId.set("w8W4HdIO")
    versionNumber.set(project.versionString())
    versionType.set(project.channel().lowercase())
    uploadFile.set(tasks.shadowJar.flatMap { it.archiveFile })
    gameVersions.set(mutableListOf("1.20", "1.20.1"))
    loaders.set(mutableListOf("paper", "purpur"))
    dependencies {
        optional.project("miniplaceholders")
    }

    syncBodyFrom.set(provider { file("README.md").readText() })
}
