import kotlin.reflect.full.memberProperties

plugins {
    java
    application
    kotlin("jvm") version "1.4.20"
    id("com.diffplug.spotless") version "5.8.2"
    id("kotlinx.benchmark") version "0.2.0-dev-20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.20"
}

group = "org.example"
version = "0212-SNAPSHOT"
var includeBenchmark = "aoc.exercises.Day3"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx")
}

application {
    mainClassName = "aoc.MainKt"
}

tasks.register<Jar>("uberJar") {
    archiveClassifier.set("uber")

    manifest {
        attributes(
            "Main-Class" to application.mainClassName
        )
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
sourceSets.all {
    java.setSrcDirs(listOf("$name/src"))
}

java {
    sourceSets.create("benchmarks")
    sourceSets["benchmarks"].java {
        srcDir("src/benchmarks/kotlin")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.openjdk.jmh:jmh-core:1.26")
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("org.apache.commons:commons-lang3:3.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

benchmark {
    configurations {
        named("main") {
            warmups = 5
            iterations = 10
            iterationTime = 3
            includes = mutableListOf(includeBenchmark)
        }
    }
    targets {
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.26"
        }
    }
}

spotless {
    ratchetFrom("origin/master")
    encoding("UTF-8")
    kotlin {
        ktlint()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}