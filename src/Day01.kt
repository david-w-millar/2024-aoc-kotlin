import kotlin.math.absoluteValue

/**
 * AoC 2024 - Day 1
 * https://adventofcode.com/2024/day/1
 *
 * Super sloppy first pass
 *
 * TODO: truncate lists after sorting
 *   - only consider the first 50 searches
 *   - I assume the first search occurs in the "Chief Historian's Office"
 *   - So maybe truncate to 49 points of data from the input file?
 */

fun main() {

    /**
     * Steps
     *   - Split each line into two strings
     *   - Convert to Pairs of Ints
     *   - Unzip Pairs into the two lists and sort them
     *   - Zip sorted lists using a transformation that finds the distance between each pair of numbers
     *   - Sum up this value
     *
     * TODO: decompose this
     * TODO: maybe use "partition" with index (even/odd/modulo 2) instead of crazy zip/unzipping
     * TODO: inline things for faster execution and experimentation
     * TODO: Run relevant code on changes
     *   - Could be "watch-exec" with a Justfile
     *   - Could be kotlin tests that are watched
     *   - Tests would provide a lot of assertions and friends to verify sanity of data ...
     */
    fun part1(lines: List<String>) =
        lines.map { input ->
            input.split(Regex("\\s+")).map { it.toInt() }
        }
            .map { Pair(it.component1(), it.component2()) }
            .unzip().let {
                // lol - this is kinda readable
                val listOne = it.first.sorted()
                val listTwo = it.second.sorted()
                listOne.zip(listTwo) { a, b ->
                    (a - b).absoluteValue
                }
            }.sum()

    @Suppress("unused")
    fun part2(input: List<String>): Int {
        return input.size
    }


    /**
     * Verify Solution with Test Data
     *
     * Input File: two lists of locationIds
     *   - represented as 2 numbers per line
     *   - the first number is a member of listOne
     *   - the second number is a member of listTwo
     *   - the lists are not sorted
     *
     *      3   4
     *      4   3
     *      2   5
     *      1   3
     *      3   9
     *      3   3
     *
     * Answer / Total Distance: 11
     *
     */
    fun checkTestInput() {
        println("::::: Checking Test Input :::::")
        val totalDistance = part1(readInput("Day01_test"))
        check(totalDistance == 11)
        println("Total distance: $totalDistance")
    }

    fun solveProblem() {
        println("::::: Solving Problem :::::")
        val totalDistance = part1(readInput("Day01"))
        println("Total distance: $totalDistance")
    }

    checkTestInput()
    solveProblem()

}
