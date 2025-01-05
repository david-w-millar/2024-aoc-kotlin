@file:Suppress("ktlint:standard:no-wildcard-imports")

package day04

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import day04.WordSearch.Companion.rotate45Raw
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

    check(WordSearch(testInput).computeResult() == 18)
    //part1()

    val testInputA = listOf("ABC", "DEF", "GHI")
    val testInputN = listOf("123", "456", "789")
    val wsA = WordSearch(testInputA)
    val wsN = WordSearch(testInputN)

    println("\n::: Original")
    wsA.prettyPrint()

    println("\n::: Rotated 45 Raw")
    val rotatedRaw = rotate45Raw(testInputA)
    println(rotatedRaw)
    rotatedRaw.forEach { println(it.joinToString("")) }
    println("---------------------")

    println("\n::: Rotated 45")
    val rotated = wsA.rotate45()
    rotated.forEach { println(it) }
    println("---------------------")

    println("\n::: Rotated 90 Raw")
    val rotatedAsList = rotatedRaw.map { it.joinToString("") }
    val rotated90Raw = rotate45Raw(rotatedAsList)
    //rotated90Raw.forEach { println(it) }
    val rotated90RawAsLines = rotated90Raw.map { it.joinToString("")}
    rotated90RawAsLines.forEach { println(it) }

    println("\n::: Rotated 135 Raw")
    val rotated135Raw = rotate45Raw(rotated90RawAsLines)
    //println(rotated135Raw)
    println("--------------------------")
    rotated135Raw.forEach { println(it) }
    val rotated135AsLines = rotated135Raw.map { it.joinToString("") }
    //println(rotated135AsLines)
    println("--------------------------")
    rotated135AsLines.forEach { println(it) }

    println("\n::: Rotated 180 Raw")
    val rotated180Raw = rotate45Raw(rotated135AsLines)
    rotated180Raw.forEach { println(it) }


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
