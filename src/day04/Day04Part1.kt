@file:Suppress(
    "ktlint:standard:no-wildcard-imports",
    "ktlint:standard:argument-list-wrapping",
    "ktlint:standard:max-line-length",
    "ktlint:standard:function-literal",
    "ktlint:standard:class-signature",
    "unused",
)

package day04

import day04.WordSearch.Companion.countForwardAndBackwards
import day04.WordSearch.Companion.rotate45Raw
import utils.Day
import java.util.concurrent.atomic.AtomicInteger

fun part1() {
    val day = Day(4, "other solution")

    data class Rotateable(val lines: List<String>) {
        fun asCleanList() = lines.map { it.replace(".","") }
    }

    fun solveProblem(lines: List<String>): Int {
        val original = lines
        val rotated45Raw = rotate45Raw(original).map { it.joinToString("") }
        val rotated90Raw = rotate45Raw(rotated45Raw).map { it.joinToString("") }
        val rotated135Raw = rotate45Raw(rotated90Raw).map { it.joinToString("") }
        val r45 = Rotateable(rotated45Raw).asCleanList()
        val r90 = Rotateable(rotated90Raw).asCleanList()
        val r135 = Rotateable(rotated135Raw).asCleanList()
        return listOf(original, r45, r90, r135).sumOf { countForwardAndBackwards(it) }
    }

    check(solveProblem(day.getTestInputLines()) == 18)
}

/** Square list of characters */
data class WordSearch(val lines: List<String>) {
    init {
        val dimensions = (listOf(lines.size) + lines.map { it.length })
        check(dimensions.distinct().count() == 1) { "Invalid WordSearch dimensions.  Expected a square, got $dimensions" }
    }

    companion object {
        const val FILLER_CHAR = '.'
        val XMAS_REGEX = "XMAS".toRegex()

        fun countForwardAndBackwards(
            lines: List<String>,
            debug: Boolean = false,
        ): Int {
            val count = AtomicInteger(0)
            lines.forEach { count.getAndAdd(XMAS_REGEX.findAll(it).toList().size) }
            lines.forEach { count.getAndAdd(XMAS_REGEX.findAll(it.reversed()).toList().size) }
            if (debug) println("::: Forwards and backwards: $count")
            return count.toInt()
        }

        fun rotate45Raw(lines: List<String>): MutableList<MutableList<Char>> {
            val n = lines.size
            val diamond = MutableList(2 * n - 1) { MutableList<Char>(2 * n - 1) { FILLER_CHAR } }
            IntRange(0, n - 1).forEach { i ->
                IntRange(0, n - 1).forEach { j ->
                    diamond[i + j][n - 1 - i + j] = lines[i][j]
                }
            }
            return diamond
        }
    }
}
