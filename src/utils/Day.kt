package utils

/**
 * Alright, this is too much, way too early ¯\_(ツ)_/¯
 *
 * Meny assumptions...
 *
 * Conventions:
 *   - Place every day's problem in its own package - eg: day01/Day01.kt
 *   - Test input files: day01/Day01_test.txt
 *   - Problem input files: day01/Day01.txt
 */
data class Day<T,S>(
    val day: T,
    val testSolution: S,
    val part: Int = 1,
) {
    private val paddedDay = getPaddedDay(day)

    private val dayName = "Day $paddedDay"
    private val partName = "Part $part"
    private val fullName = "$dayName $partName"

    private val testInputFileName = "src/day$paddedDay/Day${paddedDay}_test.txt"
    private val inputFileName = "src/day$paddedDay/Day$paddedDay.txt"

    fun getTestInputLines() = readInput(testInputFileName)
    fun getInputLines() = readInput(inputFileName)

    //  ----- Output Formatting -----
    private fun testFailureMessage() = "::::: $fullName Test Failed."
    private fun printWorkingSolution(result: Any) = println("::::: $fullName Working Solution: $result")

    //  Check Test Solutions
    private fun checkTestSolution(result: Any) = check(result == testSolution) { testFailureMessage() }

    fun printWorkingSolutionAfterTest(
        result: Any,
        testResult: Any,
    ) = checkTestSolution(testResult).also {
        printWorkingSolution(result)
    }

    // Generate Part 2
    fun <S> part2(testSolution: S) = Day(day, testSolution, 2)

    private companion object {
        fun <T> getPaddedDay(day: T): String {
            check((day is Int) || (day is String)) { "Day must be either a String or an Int" }
            return day.toString().padStart(2,'0')
        }
    }
}
