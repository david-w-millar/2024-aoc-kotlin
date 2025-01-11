package day01

import utils.Day
import utils.elementCounts
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.absoluteValue

fun main() {
    fun getLists(lines: List<String>) =
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
        val count = AtomicInteger(0)
        leftList.forEach {
            count.addAndGet(it * numberCounts.getOrDefault(it, 0))
        }
        return count.get()
    }

    val problem = Day(1, 11)
    problem.run {
        printWorkingSolutionAfterTest(part1(getInputLines()), part1(getTestInputLines()))
    }

    problem.part2(31).run {
        printWorkingSolutionAfterTest(part2(getInputLines()), part2(getTestInputLines()))
    }
}
