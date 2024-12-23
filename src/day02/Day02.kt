package day02

import utils.readInput
import java.util.concurrent.atomic.AtomicInteger

@Suppress("ktlint:standard:no-empty-first-line-in-method-block")
/**
 * [AoC 2024 - Day 2](https://adventofcode.com/2024/day/2)
 */

fun main() {

    fun String.toListOfInts(regex: Regex = """\s+""".toRegex()) =
        trim()
            .split(regex)
            .map { it.toInt() }

    fun List<Int>.startsByIncreasing() = run { get(0) < get(1) }

    fun List<Int>.startsByDecreasing() = !startsByIncreasing()

    fun List<Int>.isStrictlyIncreasing() = run { sorted() == this }

    fun List<Int>.isStrictlyIncreasingByAtMost(step: Int = 3) =
        windowed(2,1).all {
            (it.first() < it.last()).and( it.last() - it.first() <= step)
        }

    fun List<Int>.isStrictlyMonotonic(): Boolean {
        if (toSet().size != size) return false
        val increasing = get(1) >= get(0) // Determine initial direction
        for (i in 1 until size - 1) {
            if (increasing && get(i + 1) < get(i)) {
                return false // Violates increasing monotonicity
            } else if (!increasing && get(i + 1) > get(i)) {
                return false // Violates decreasing monotonicity
            }
        }
        return true
    }

    fun part1(lines: List<String>): Int {
        val safeCount = AtomicInteger(0)
        lines.forEach { line ->
            line.toListOfInts()
                .run {
                println("----------------------------------")
                println(this)
                println(isStrictlyMonotonic())

//                val normalized = if (startsByDecreasing() && isStrictlyIncreasing()) sorted() else this
//                val isStrictlyIncreasing = normalized.isStrictlyIncreasing()
//                val isStrictlyIncreasingBy = normalized.isStrictlyIncreasingByAtMost()

//                println("--------------------------------")
//                safeCount.getAndIncrement()
//                println(line)
//                println(normalized)
//                println(isStrictlyIncreasing)
//                println(isStrictlyIncreasingBy)
            }
        }

        return safeCount.get()
    }

    fun part2(input: List<String>) {}

    println(part1(readInput("day02/Day02_test")))
//    println(part1(readInput("day02/Day02")))
}
