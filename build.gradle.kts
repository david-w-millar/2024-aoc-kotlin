plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.power-assert") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
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

// TODO: Configure this for the task at hand
ktlint {
    coloredOutput = true
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
