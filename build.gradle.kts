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

    //implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
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
        freeCompilerArgs.add("-Xmulti-dollar-interpolation")
    }
}


/**
 * Enable "Power Asserts" for all source sets in the project.
 * See https://kotlinlang.org/docs/power-assert.html
 *
 * NOTE: kotlin.assert only works in tests by default,
 *       without additional configuration
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


/**
 * TODO: Use different thing or write new thing.
 */
//configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
//    version.set("1.5.0")
//    debug.set(true)
//    verbose.set(true)
//    outputToConsole.set(true)
//    // Does this even do anything?
//    outputColorName.set("RED")
//    ignoreFailures.set(false)
//    reporters {
//        reporter(ReporterType.PLAIN)
//        reporter(ReporterType.CHECKSTYLE)
//        reporter(ReporterType.HTML)
//        reporter(ReporterType.JSON)
//        reporter(ReporterType.PLAIN_GROUP_BY_FILE)
//        reporter(ReporterType.SARIF)
//    }
//}
