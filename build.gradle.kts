import kotlin.reflect.full.memberProperties

plugins {
    java
    application
    kotlin("jvm") version "1.4.20"
    id("co.uzzu.dotenv.gradle") version "1.1.0"
    id("com.diffplug.spotless") version "5.8.2"
    id("kotlinx.benchmark") version "0.2.0-dev-20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.20"
}
group = "org.example"
version = "0212-SNAPSHOT"
val include: String by project

tasks.withType<Test> {
    testLogging.showStandardStreams = true
    useJUnitPlatform()
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx")
    maven("https://jitpack.io")
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
    implementation("com.github.kittinunf.fuel:fuel:2.3.0")
    implementation("com.google.guava:guava:30.0-jre")
    implementation("org.openjdk.jmh:jmh-core:1.26")
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("org.apache.commons:commons-lang3:3.10")
    implementation("org.jsoup:jsoup:1.13.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

benchmark {
    configurations {
        named("main") {
            warmups = 5
            iterations = 10
            iterationTime = 3
            if (project.hasProperty("include")) {
                println(include)
                includes = mutableListOf(include)
            }
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
        ktfmt()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

tasks.register<JavaExec>("codegen") {
    environment("AOC_SESSION", env.AOC_SESSION.value)
    if(project.hasProperty("d")) {
        args("-d")
        args(properties["d"])
    }
    if(project.hasProperty("s")) {
        args("-s")
        args(properties["s"])
    }
    classpath = sourceSets.test.get().runtimeClasspath
    main = "aoc.utils.codegen.CodegenKt"
}

tasks.register<JavaExec>("readme") {
    environment("AOC_SESSION", env.AOC_SESSION.value)
    if(project.hasProperty("d")) {
        args("-d")
        args(properties["d"])
    }
    classpath = sourceSets.test.get().runtimeClasspath
    main = "aoc.utils.codegen.ReadmeWriterKt"
}