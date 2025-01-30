package day06

import utils.Day

/**
 * [AoC 2024 - Day 3](https://adventofcode.com/2024/day/6)
 */
fun main() {
    val day = Day(6, "test solution")

    fun part1(lines: List<String>) {
        println(lines)
    }
    fun part2(lines: List<String>) {
        println(lines)
    }

    day.run {
        printWorkingSolutionAfterTest(part1(day.getInputLines()), part1(day.getTestInputLines()))
        part2("part2 test solution").printWorkingSolutionAfterTest(
            part2(day.getInputLines()),
            part2(day.getTestInputLines())
        )
    }
}
