package day02

import utils.readInput
import java.util.concurrent.atomic.AtomicInteger

/**
 * [AoC 2024 - Day 2](https://adventofcode.com/2024/day/2)
 */
fun main() {

    fun String.toListOfInts(regex: Regex = """\s+""".toRegex()) = trim().split(regex).map { it.toInt() }

    fun List<Int>.startsByIncreasing() = run { get(0) < get(1) }

    fun List<Int>.startsByDecreasing() = !startsByIncreasing()

    fun List<Int>.containsDuplicates() = (toSet().size != size)
    fun List<Int>.isStrictlyIncreasing() = run { sorted() == this }

    fun List<Int>.isStrictlyIncreasingByAtMost(maxStep: Int = 3) =
        windowed(2,1).all {
            (it.first() < it.last()).and(it.last() - it.first() <= maxStep)
        }

//    fun List<Int>.countUnsafePlans(maxStep: Int = 3, maxUnsafePlans: Int = 1): Int {
//        val unsafePlans = AtomicInteger(0)
//        windowed(2,1).all {
//            //if( !(it.first() < it.last() && it.last() - it.first() <= maxStep) ) unsafePlans.getAndIncrement()
//            if( !(it.first() < it.last() && it.last() - it.first() <= maxStep) ) unsafePlans.getAndIncrement()
//        }
//        return unsafePlans.get()
//    }

    /** Could use a better name... */
    fun List<Int>.isStrictlyMonotonic(): Boolean {
        if (containsDuplicates()) return false
        val increasing = get(1) >= get(0) // Determine initial direction
        for (i in 1 until (size - 1)) {
            if (increasing && get(i + 1) < get(i)) {
                return false // Violates increasing monotonicity
            } else if (!increasing && get(i + 1) > get(i)) {
                return false // Violates decreasing monotonicity
            }
        }
        return true
    }

    fun List<Int>.isSafeReport() =
        isStrictlyMonotonic() &&
            !containsDuplicates() &&
            sorted().isStrictlyIncreasingByAtMost()

    fun getSafeCount(lines: List<String>): Int {
        val safeCount = AtomicInteger(0)
        lines.forEach { line ->
            line
                .toListOfInts()
                .run {
                    if (isSafeReport()) {
                        safeCount.getAndIncrement()
                    }
                }
        }

        return safeCount.get()
    }

    fun part1(lines: List<String>): Int {
        val safeCount = AtomicInteger(0)
        lines.forEach { line ->
            line
                .toListOfInts()
                .run {
                    if (isSafeReport()) {
                        safeCount.getAndIncrement()
                    }
                }
        }

        return safeCount.get()
    }

    fun part2(lines: List<String>) {
        val safeCount = AtomicInteger(0)
        lines.forEach { line ->
            line
                .toListOfInts()
                .run {
                    if(
                        isStrictlyMonotonic()
                        &&
                        !containsDuplicates()
                        &&
                        sorted().isStrictlyIncreasingByAtMost()
                    ) {
                        safeCount.getAndIncrement()
                    }
                }
        }
    }

    println("::: Part1 Test: ${part1(readInput("day02/Day02_test"))}")
    println("::: Part1     : ${part1(readInput("day02/Day02"))}")

//    println("::: Part2 Test: ${part1(readInput("day02/Day02_test"))}")
//    println("::: Part2     : ${part1(readInput("day02/Day02"))}")
}
