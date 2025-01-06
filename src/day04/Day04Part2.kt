@file:Suppress("ktlint:standard:statement-wrapping")

package day04

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextColors.green
import com.github.ajalt.mordant.rendering.TextStyles.bold
import day04.Day04Inputs.testInputA4
import utils.checkIsASquare
import utils.prettyPrintAsGrid
import java.util.concurrent.atomic.AtomicInteger


fun main() {
    val testInput = testInputA4
    val expectedTestGrids = listOf(
        listOf("ABC", "EFG", "IJK"),
        listOf("BCD", "FGH", "JKL"),
        listOf("EFG", "IJK", "MNO"),
        listOf("FGH", "JKL", "NOP")
    )
    println(testInput)
    testInput.prettyPrintAsGrid()

    val grid = CharGrid(testInput)
    val grids = grid.enumerate3x3Grids()

    println(grids)
    grids.forEach { it.prettyPrintAsGrid() }
    check(grids == expectedTestGrids)

}

data class CharGrid(val lines: List<String>) {
    init { lines.checkIsASquare() }

    fun enumerate3x3Grids(): List<List<String>> {
        val rows = lines.size
        val cols = lines[0].length
        check(rows >= 3 || cols >= 3) { "Invalid CharGrid: must be at least 3x3 characters. ($rows, $cols)" }
        val grids = mutableListOf<List<String>>()
        for (i in 0 until rows - 2) {
            for (j in 0 until cols - 2) {
                val subGrid = (i..i + 2).map { row ->
                    (j..j + 2).map { col ->
                        lines[row][col]
                    }
                }
                grids.add(subGrid.map { it.joinToString("") })
            }
        }
        return grids.toList()
    }

}
