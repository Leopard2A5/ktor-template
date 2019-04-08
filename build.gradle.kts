import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    id("com.github.johnrengelman.shadow") version("5.0.0")
    application
}

group = "de.perschon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven(
        url = "https://jitpack.io"
    )
}

application {
    mainClassName = "de.perschon.ktortemplate.ApplicationKt"
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("io.ktor:ktor-server-netty:1.1.3")
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("com.uchuhimo:konf:0.13.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}