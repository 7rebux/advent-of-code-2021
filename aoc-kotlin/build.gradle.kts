import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.5.31"
}

application {
    mainClass.set("de.nosswald.aoc.Runner")
}

group = "de.nosswald"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.reflections", "reflections", "0.9.12")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
