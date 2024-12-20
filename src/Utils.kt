import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

// ------------------------- IO -------------------------

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/** The cleaner shorthand for printing output. */
fun Any?.println() = println(this)

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

// ------------------------- Day Specific Output -------------------------

/**
 * Aight, this is too much, way too early ¯\_(ツ)_/¯
 *
 * Meny assumptions...
 *
 * Conventions:
 *   - Place every day's problem in it's own package - eg: day01/Day01.kt
 *   - Place test input files like so - eg: day01/Day01_test.txt
 *   - Place problem input files like so - eg: day01/Day01.txt
 *
 * TODO: use templates for this
 *
 * TODO: brief example
 * TODO: Maybe just have a parent class?
 * TODO: move to own file
 * TODO: meh... decapitalize, or ignore package naming conventions. We're already ignoring a lot of conventions!
 */
data class Day(
    val day: Int,
    val part: Int = 1,
) {
    private val dayName = dayName(day)
    private val partName = "Part $part"
    private val fullName = "$dayName $partName"

    // Conventions
    private val testFileName = "${dayName.decapitalize()}/${dayName}_test"
    private val inputFileName = "${dayName.decapitalize()}/$dayName"

    //  Check solutions - might want to make these more public for faster dev loops.
    private fun checkTestSolution(
        result: Any,
        expected: Any,
    ) = check(result == expected) { testFailureMessage() }

    private fun printWorkingSolution(result: Any) = println("::::: $fullName Working Solution: $result")

    fun printWorkingSolutionAfterTest(
        result: Any,
        testResult: Any,
        testExpected: Any,
    ) = checkTestSolution(testResult, testExpected).also {
        printWorkingSolution(result)
    }

    // Generate failure messages
    private fun testFailureMessage() = "::::: $fullName Test Failed."

    // Get inputs
    fun testFileLines() = readInput(testFileName)

    fun inputFileLines() = readInput(inputFileName)

    // Generate Part 2
    // TODO: maybe rename this class DayPart? The semantics break down a bit. Reconsider.
    fun part2() = Day(day, 2)

    // override fun toString() = fullName

    private companion object {
        fun padDay(day: Int) = day.toString().padStart(2, '0')

        fun dayName(day: Int) = "Day${padDay(day)}"
    }
}

// SAMs for problem parts
// TODO: use these to avoid distinguishing between problem parts, and test cases, maybe?
// TODO: Might declutter daily problems
// fun interface Part<I,O> { fun part1(input: I): O }
