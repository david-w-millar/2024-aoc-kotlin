package utils

/**
 * Alright, this is too much, way too early ¯\_(ツ)_/¯
 *
 * Meny assumptions...
 *
 * Conventions:
 *   - Place every day's problem in its own package - eg: day01/Day01.kt
 *   - Place test input files like so - eg: day01/Day01_test.txt
 *   - Place problem input files like so - eg: day01/Day01.txt
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
    // TODO: maybe rename this class DayPart or get rid of it? The semantics break down a bit. Reconsider.
    fun part2() = Day(day, 2)

    private fun padDay(day: Int) = day.toString().padStart(2, '0')
    private fun dayName(day: Int) = "Day${padDay(day)}"
}
