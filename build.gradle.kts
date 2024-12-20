plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.power-assert") version "2.1.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

dependencies {
    // This might be useful for dumping data later
    // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3") // Replace with the latest version
}

sourceSets {
    main {
        kotlin.srcDir("src")
        resources.srcDir("src/**/*.txt")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11.1"
    }
}

/**
 * Enable "Power Asserts" for all source sets in the project.
 * See https://kotlinlang.org/docs/power-assert.html
 */
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    includedSourceSets = sourceSets.map { it.name }
    functions = listOf("kotlin.assert", "kotlin.check", "kotlin.require")
}
