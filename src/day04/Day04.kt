@file:Suppress("ktlint:standard:no-wildcard-imports")

package day04

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import utils.Day
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val day = Day(4, "test solution")

    val testInput = day.getTestInputLines()
    val input = day.getInputLines()

    fun part1() {
        check(WordSearch(testInput).computeResult() == 18)
        println("::: Solution: ${WordSearch(input).computeResult()}")
    }

    //fun part2(lines: List<String>) { }

    part1()

}

/**
 * Square list of characters
 */
data class WordSearch(
    val lines: List<String>
) {
    init {
        val dimensions = (listOf(lines.size) + lines.map { it.length })
        check(dimensions.distinct().count() == 1) { "Invalid wordsearch dimensions.  Expected a square, got $dimensions" }
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

    fun rotate45(clean: Boolean = true): List<String> {
        val n = lines.size
        val diamond = MutableList(2 * n - 1) { MutableList<Char?>(2 * n - 1) { FILLER_CHAR } }
        IntRange(0, n - 1).forEach { i ->
            IntRange(0, n - 1).forEach { j ->
                diamond[i + j][n - 1 - i + j] = lines[i][j]
            }
        }
        return diamond.map { it.joinToString("").replace(FILLER_CHAR.toString(), "") }.toList()
    }

    fun cleanDiamond(lines: List<String>) {
        lines.map { FILLER_CHAR_REGEX.replace(it) { "" } }
    }


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
    }
}
