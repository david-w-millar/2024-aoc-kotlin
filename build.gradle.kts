@file:Suppress("SpellCheckingInspection")

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.power-assert") version "2.1.0"
    // id("org.jetbrains.kotlinx.dataframe") version "0.15.0"
    // id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    // Pretty Colors!
    implementation("com.github.ajalt.mordant:mordant-core:3.0.1")
    implementation("com.github.ajalt.mordant:mordant:3.0.1")
    implementation("com.github.ajalt.mordant:mordant-markdown:3.0.1")

    // For permutations
    implementation("com.google.guava:guava:33.4.0-jre")
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

kotlin {
    compilerOptions {
        //
        freeCompilerArgs.add("-Xmulti-dollar-interpolation")
    }
}


/**
 * Enable "Power Asserts" for all source sets in the project.
 * See https://kotlinlang.org/docs/power-assert.html
 * NOTE: kotlin.assert won't work as expected without additional configuration
 */
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    includedSourceSets = sourceSets.map { it.name }
    functions =
        listOf(
            "kotlin.assert",
            "kotlin.check",
            "kotlin.require",
            "kotlin.test.assertTrue",
            "kotlin.test.assertEquals",
            "kotlin.test.assertNull"
        )
}

// https://github.com/Kotlin/multik
//implementation("org.jetbrains.kotlinx:multik-core:0.2.3")
//implementation("org.jetbrains.kotlinx:multik-default:0.2.3")
//implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
// https://github.com/SciProgCentre/kmath?tab=readme-ov-file
//implementation("space.kscience:kmath-core:0.3.1")

