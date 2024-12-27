package day02

import utils.readInput
import java.util.concurrent.atomic.AtomicInteger

/**
 * [AoC 2024 - Day 2](https://adventofcode.com/2024/day/2)
 */
fun main() {

    fun String.toListOfInts(regex: Regex = """\s+""".toRegex()) = trim().split(regex).map { it.toInt() }

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

    fun part1(lines: List<String>): Int {
        val safeCount = AtomicInteger(0)
        lines.forEach { line ->
            line
                .toListOfInts()
                // TODO: use .count()
                .run {
                    if (isSafeReport()) {
                        safeCount.getAndIncrement()
                    }
                }
        }
        return safeCount.get()
    }

    fun part2(input: List<String>) = "TODO ${input.size}"

    val partOneSolution = part1(readInput("day02/Day02_test"))
    check(partOneSolution == 2)
    println("::: Part1 Test: $partOneSolution")
    println("::: Part1     : ${part1(readInput("day02/Day02"))}")

    println("::: Part2 Test: ${part2(readInput("day02/Day02_test"))}")
    println("::: Part2     : ${part2(readInput("day02/Day02"))}")
}
