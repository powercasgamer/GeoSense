import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.the

//fun Project.channel(): String {
//    val version = project.version as String
//    return if (version.endsWith("-SNAPSHOT")) {
//        "Beta"
//    } else {
//        "Release"
//    }
//}

val Project.libs: LibrariesForLibs
    get() = the()

fun Project.channel(): String {
    return if (project.version.toString().endsWith("-SNAPSHOT")) {
        "Beta"
    } else {
        "Release"
    }
}

fun Project.versionString(): String = this.version as String
