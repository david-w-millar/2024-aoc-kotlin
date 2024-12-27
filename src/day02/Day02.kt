package day02

import utils.readInput

/**
 * [AoC 2024 - Day 2](https://adventofcode.com/2024/day/2)
 */
fun main() {

    fun String.toListOfInts(regex: Regex = """\s+""".toRegex()): List<Int> = trim().split(regex).map { it.toInt() }
    fun String.toLines() = readInput(this)
    fun String.toReports() = this.toLines().map { it.toListOfInts() }

    // TODO: this shouldn't be necessary
    fun List<Int>.containsDuplicates() = (toSet().size != size)

    fun List<Int>.isStrictlyIncreasingByAtMost(maxStep: Int = 3) =
        windowed(2,1).all {
            (it.first() < it.last()).and(it.last() - it.first() <= maxStep)
        }

    /** Could use a better name... */
    fun List<Int>.isStrictlyMonotonic(): Boolean {
        if (containsDuplicates()) return false
        val increasing = get(1) >= get(0) // Determine initial direction
        for (i in 1 until (size - 1)) {
            if (increasing && get(i + 1) < get(i)) {
                return false
            } else if (!increasing && get(i + 1) > get(i)) {
                return false
            }
        }
        return true
    }

    fun List<Int>.isSafeReport() =
        isStrictlyMonotonic() &&
            !containsDuplicates() &&
            sorted().isStrictlyIncreasingByAtMost()

    fun part1(reports: List<List<Int>>) = reports.count { it.isSafeReport() }

    fun List<Int>.getSubsets() : List<List<Int>> {
        val subsets = mutableListOf<Int>()
        val jawn = 0 .. (this.size - 1)
        jawn.forEach { println(it)}
//        println(0 .. this.size)
//        for(i in 0 .. this.size) {
//            println(this.get(i))
//        }

        return listOf(listOf())
    }

    fun part2(reports: List<List<Int>>) : Int {

        val report = listOf(7,6,4,2,1)
        println(report.getSubsets())

        return reports.count {
            it.getSubsets().any { report -> report.isSafeReport() }
        }
    }

//    check(part1("day02/Day02_test".toReports()) == 3)
//    check(part2("day02/Day02_test".toReports()) == 4)

    val partOneSolution = part1("day02/Day02_test".toReports())
    check(partOneSolution == 2)
    println("::: Part1 Test: $partOneSolution")
    println("::: Part1     : ${part1("day02/Day02".toReports())}")

    val partTwoTest = part2("day02/Day02_test".toReports())
    println("::: Part2 Test: $partTwoTest")
    println("::: Part2     : ${part2("day02/Day02".toReports())}")
}
