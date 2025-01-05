@file:Suppress("ktlint:standard:no-wildcard-imports")

package day04

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import day04.WordSearch.Companion.countForwardAndBackwards
import day04.WordSearch.Companion.rotate45Raw
import utils.Day
import java.util.concurrent.atomic.AtomicInteger

/**
 * This is disgusting. lol
 * Move reusable parts elsewhere.
 * Make classes and functions coherent.
 * Eliminate repetition.
 */
fun main() {
    val day = Day(4, "test solution")

    fun solveProblem(lines: List<String>): Int {
        val original = lines
        val rotated45Raw = rotate45Raw(original).map { it.joinToString("") }
        val rotated90Raw = rotate45Raw(rotated45Raw).map { it.joinToString("") }
        val rotated135Raw = rotate45Raw(rotated90Raw).map { it.joinToString("") }

        val r45 = Rotateable(rotated45Raw).asCleanList()
        val r90 = Rotateable(rotated90Raw).asCleanList()
        val r135 = Rotateable(rotated135Raw).asCleanList()

        val toCheck = listOf(original, r45, r90, r135)
        val sum = toCheck.sumOf { countForwardAndBackwards(it) }
        println(sum)
        return sum
    }

    check(solveProblem(day.getTestInputLines()) == 18)
    println(solveProblem(day.getInputLines()))
}

data class Rotateable(
    val lines: List<String>
) {
    fun prettyPrint() = lines.forEach { println(it) }
    fun asCleanList() = lines.map { it.replace(".","") }
}

/**
 * Square list of characters
 */
data class WordSearch(
    val lines: List<String>
) {
    init {
        val dimensions = (listOf(lines.size) + lines.map { it.length })
        check(
            dimensions.distinct().count() == 1
        ) { "Invalid WordSearch dimensions.  Expected a square, got $dimensions" }
    }

    fun computeResult(debug: Boolean = false): Int {
        val rotated90 = rotated90()
        val diamond = rotate45()
        val reverseDiamond = rotated90().rotate45()

        val horizontalCount = countForwardAndBackwards(debug)
        val verticalCount = rotated90.countForwardAndBackwards(debug)
        val diagonalCount1 = countForwardAndBackwards(diamond)
        val diagonalCount2 = countForwardAndBackwards(reverseDiamond)

        if(debug) {
            println(blue("--------- Counts -----------"))
            println("::: Horizontal: ${green(horizontalCount.toString())}")
            println("::: Vertical:   ${green(verticalCount.toString())}")
            println("::: Diagonal 1: ${green(diagonalCount1.toString())}")
            println("::: Diagonal 2: ${green(diagonalCount2.toString())}")
            println(blue("\n-----------  Diagonal 1 -----------"))
            diamond.forEach { println(it) }
            println(blue("-----------  Diagonal 2 -----------"))
            reverseDiamond.forEach { println(it) }
            println(blue("-----------------------------"))
        }

        return horizontalCount + verticalCount + diagonalCount1 + diagonalCount2
    }

    fun countForwardAndBackwards(debug: Boolean = false) = countForwardAndBackwards(lines, debug)

    fun rotate45() = rotate45(lines)

    fun rotated90() = WordSearch(rotate90())
    fun rotate90(): List<String> {
        val len = lines.size
        val rotated = buildList { lines.forEach { add(StringBuilder()) } }
        for(l in 0 .. len - 1) {
            for (r in 0..len - 1) {
                rotated.get(l).append(lines.get(r).get(l))
            }
        }
        return rotated.map { it.toString() }
    }

    fun prettyPrint() {
        println(lines.joinToString("\n").replace("XMAS".toRegex()) { mr -> green(bold(mr.value)) })
    }

    companion object {
        const val FILLER_CHAR = '.'
        val FILLER_CHAR_REGEX = FILLER_CHAR.toString().toRegex()
        const val XMAS = "XMAS"
        val XMAS_REGEX = XMAS.toRegex()

        fun countForwardAndBackwards(lines: List<String>, debug: Boolean = false): Int {
            val count = AtomicInteger(0)
            // TODO: use splat operator
            lines.forEach { count.getAndAdd(XMAS_REGEX.findAll(it).toList().size) }
            lines.forEach { count.getAndAdd(XMAS_REGEX.findAll(it.reversed()).toList().size) }
            if (debug) println("::: Forwards and backwards: $count")
            return count.toInt()
        }

        fun rotate45(lines: List<String>): List<String> {
            val diamond = rotate45Raw(lines)
            return diamond.map { it.joinToString("").replace(FILLER_CHAR.toString(), "") }
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
