package day02

import utils.Day
import utils.elementCounts
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.absoluteValue

/**
 * [AoC 2024 - Day 2](https://adventofcode.com/2024/day/2)
 */

fun main() {

    fun part1(lines: List<String>) {}
    fun part2(input: List<String>) {}

    val problem = Day(2)

    problem.run {
        printWorkingSolutionAfterTest(part1(inputFileLines()), part1(testFileLines()), 11)
    }

    problem.part2().run {
        printWorkingSolutionAfterTest(part2(inputFileLines()), part2(testFileLines()), 31)
    }
}
