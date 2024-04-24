plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
}

group = "io.morimanga.riku"
version = "1.0-RELEASE"

application {
    mainClass.set("io.morimanga.riku.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(libs.ktor.core)
    implementation(libs.ktor.server)
    implementation(libs.ktor.serialization.core)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.cors)
    implementation(libs.ktor.routing)

    implementation(libs.sqlite)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.datetime)

    implementation(libs.logging)

    implementation(project(":extensions"))
}
