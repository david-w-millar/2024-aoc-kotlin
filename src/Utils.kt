import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText


/* ------------------------- IO ------------------------- */

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/** The cleaner shorthand for printing output. */
fun Any?.println() = println(this)


/* ------------------------- Hashing ------------------------- */

/** Converts string to md5 hash. */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


/* ------------------------- Collections ------------------------- */

/**
 * This could use a better name, but it's similar to Python's Counter.
 * https://docs.python.org/3/library/collections.html#collections.Counter
 * There must be a better way...
 */
fun <T> List<T>.elementCounts() = groupingBy { it }.eachCount()

/* ------------------------- Day Specific Output ------------------------- */

/**
 * TODO: brief example
 * TODO: move to own file
 */
data class Day(val day: Int, val part: Int = 1) {
    private val dayName = dayName(day)
    private val partName = "Part $part"
    private val name = "$dayName $partName"
    val testFileName = "${dayName}_test"
    val inputFileName = dayName

    fun checkTestSolution(result: Any, expected: Any) = check(result == expected) { testFailureMessage() }
    fun printWorkingSolution(solution: Any) = println("::::: $name Working Solution: $solution")

    fun testFileLines() = readInput(testFileName)
    fun inputFileLines() = readInput(inputFileName)
    fun part2() = Day(day, 2)

    private fun testFailureMessage() = "::::: $name Test Failed."

    override fun toString() = name

    private companion object {
        fun padDay(day: Int) = day.toString().padStart(2,'0')
        fun dayName(day: Int) = "Day${padDay(day)}"
    }
}
