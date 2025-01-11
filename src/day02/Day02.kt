package day02

import utils.readInput

fun main() {

    fun String.toListOfInts(regex: Regex = """\s+""".toRegex()): List<Int> = trim().split(regex).map { it.toInt() }
    fun String.toLines() = readInput(this)
    fun String.toReports() = this.toLines().map { it.toListOfInts() }

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

    fun List<Int>.isSafeReport(): Boolean =
        isStrictlyMonotonic() &&
            !containsDuplicates() &&
            sorted().isStrictlyIncreasingByAtMost()

    fun part1(reports: List<List<Int>>) = reports.count { it.isSafeReport() }

    fun getReportsWithOneLevelRemoved(list: List<Int>): List<List<Int>> {
        val subsets =
            buildList {
                add(list)
                for(i in list.indices) {
                    add(list.filterIndexed { removeIndex, _ -> removeIndex != i })
                }
            }
        return subsets
    }

    fun part2(reports: List<List<Int>>) =
        reports.count {
            getReportsWithOneLevelRemoved(it).any { report -> report.isSafeReport() }
        }

    val partOneSolution = part1("src/day02/Day02_test.txt".toReports())
    check(partOneSolution == 2)
    //println("::: Part1 Test: $partOneSolution")
    println("::: Part1     : ${part1("src/day02/Day02.txt".toReports())}")

    val partTwoTest = part2("src/day02/Day02_test.txt".toReports())
    check(partTwoTest == 4)
    //println("::: Part2 Test: $partTwoTest")
    println("::: Part2     : ${part2("src/day02/Day02.txt".toReports())}")
}
