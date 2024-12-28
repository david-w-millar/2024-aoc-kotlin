package utils

import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale
import kotlin.io.path.Path
import kotlin.io.path.readText

// ------------------------- IO -------------------------

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path(name).readText().trim().lines()

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

// ------------------------- Day Related Things -------------------------

//fun <T> getPaddedDay(day: T): String {
//    check((day is Int) || (day is String)) { "Day must be either a String or an Int" }
//    return day.toString().padStart(2,'0')
//}
//
//fun <T> getTestInputFile(day: T) = "src/day${getPaddedDay(day)}/Day${getPaddedDay(day)}_test.txt"
//fun <T> getInputFile(day: T) = "src/day${getPaddedDay(day)}/Day${getPaddedDay(day)}.txt"
//fun <T> getTestInput(day: T) = readInput(getTestInputFile(day))
//fun <T> getInput(day: T) = readInput(getInputFile(day))
