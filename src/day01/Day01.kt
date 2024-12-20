import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.absoluteValue

/**
 * AoC 2024 - Day 1
 * https://adventofcode.com/2024/day/1
 */

fun main() {
    // TODO: better name, move to utils, clean up
    fun getLists(lines: List<String>) =
        // TODO: extract, move elsewhere
        lines
            .map { input ->
                input.split(Regex("\\s+")).map { it.toInt() }
            }.map { Pair(it.component1(), it.component2()) }
            .unzip()

    fun part1(lines: List<String>) =
        getLists(lines)
            .let {
                // lol - this is kinda readable
                val listOne = it.first.sorted()
                val listTwo = it.second.sorted()
                listOne.zip(listTwo) { a, b ->
                    (a - b).absoluteValue
                }
            }.sum()

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = getLists(input)
        val numberCounts = rightList.elementCounts()
        // TODO: eliminate count
        val count = AtomicInteger(0)
        leftList.forEach {
            count.addAndGet(it * numberCounts.getOrDefault(it, 0))
        }
        return count.get()
    }

    val problem = Day(1)

    problem.run {
        printWorkingSolutionAfterTest(part1(inputFileLines()), part1(testFileLines()), 11)
    }

    problem.part2().run {
        printWorkingSolutionAfterTest(part2(inputFileLines()), part2(testFileLines()), 31)
    }
}
