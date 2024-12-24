package utils

import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale
import kotlin.io.path.Path
import kotlin.io.path.readText

// ------------------------- IO -------------------------

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

// ------------------------- Hashing -------------------------

/** Converts string to md5 hash. */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

/** I really don't know why the original is deprecated */
fun Any.decapitalize() = this.toString().replaceFirstChar { it.lowercase(Locale.getDefault()) }

// ------------------------- Collections -------------------------

/**
 * This could use a better name, but it's similar to Python's Counter.
 * https://docs.python.org/3/library/collections.html#collections.Counter
 * There must be a better way...
 */
fun <T> List<T>.elementCounts() = groupingBy { it }.eachCount()

//fun String.toListOfInts() = this.trim().split(regex = """\s+""".toRegex()).map { it.toInt() }
//fun String.toListOfInts() = this.trim().split(regex = """\s+""".toRegex())
