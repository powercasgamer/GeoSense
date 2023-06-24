plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
    implementation(libs.minotaur)
    implementation(libs.hangar.publish)
    implementation(libs.titan)
    implementation(libs.indra.common)
    implementation(libs.indra.git)
    implementation(libs.shadow)
    implementation(libs.run.task)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    target {
        compilations.configureEach {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
}