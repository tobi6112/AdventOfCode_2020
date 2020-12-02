plugins {
    java
    kotlin("jvm") version "1.4.20"
    id("com.diffplug.spotless") version "5.8.2"
    id("kotlinx.benchmark") version "0.2.0-dev-20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx")
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

/*kotlin {
    sourceSets["benchmarks"].apply {
        kotlin.srcDir("src/benchmarks/kotlin")
    }
}

sourceSets {
    val main by getting
    val test by getting
    val benchmarks by creating {
        kotlin {

            srcDir("src/benchmarks/kotlin")
            compileClasspath += main.output + test.output
            runtimeClasspath += main.output + test.output
        }
    }
}*/

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.openjdk.jmh:jmh-core:1.26")
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

benchmark {
    configurations {
        named("main") {
            warmups = 5
            iterations = 10
            iterationTime = 3
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