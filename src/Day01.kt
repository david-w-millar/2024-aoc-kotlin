import kotlin.math.absoluteValue

/**
 * AoC 2024 - Day 1
 * https://adventofcode.com/2024/day/1
 *
 * Super sloppy first pass
 */

fun main() {

    /**
     * Steps
     *   - Split each line into two strings
     *   - Convert to Pairs of Ints
     *   - Unzip Pairs into the two lists and sort them
     *   - Zip sorted lists using a transformation that finds the distance between each pair of numbers
     *   - Sum up this value
     */
    fun part1(input: List<String>) =
        input.map {
            it.split(Regex("\\s+")).map { it.toInt() }
        }
            .map { Pair(it.component1(), it.component2()) }
            .unzip().let {
                // lol - this is kinda readable
                val listOne = it.first.sorted()
                val listTwo = it.second.sorted()
                listOne.zip(listTwo) { a,b ->
                    (a - b).absoluteValue
                }
            }.sum()

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

    checkTestInput()

}

