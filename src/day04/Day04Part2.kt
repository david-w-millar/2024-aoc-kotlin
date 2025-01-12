@file:Suppress(
    "ktlint:standard:statement-wrapping",
    "ktlint:standard:class-signature",
    "ktlint:standard:chain-method-continuation",
)

package day04

import day04.Grid3x3.CharGrid
import utils.checkIsASquare
import utils.printSolution

fun part2() {
    val valid = listOf("MMSS", "MSMS", "SMSM", "SSMM")
    fun part2() {
        CharGrid(Day04Inputs.input)
            .get3x3GridCollection()
            .filterByMiddleA()
            .grids
            .count { valid.contains(it.getStringRep()) }
            .printSolution(4,2)
    }
    part2()
}

data class Grid3x3Collection(val grids: List<Grid3x3>) {
    init { check(grids.isNotEmpty()) { "No grids were provided" } }
    fun filterByMiddleA() = Grid3x3Collection(grids.filter { it.hasMiddleA() }.toList())
    fun size() = grids.size
}

data class Grid3x3(val lines: List<String>) {
    init { check(lines.size == 3) { "Lines gotta be 3, dawg" } }

    // Upper Left, Upper Right, Lower Left, Lower Right
    fun ul() = lines[0][0]
    fun ur() = lines[0][2]
    fun ll() = lines[2][0]
    fun lr() = lines[2][2]

    fun getStringRep() = listOf(ul(),ur(),ll(),lr()).joinToString("")
    fun hasMiddleA() = lines[1][1] == 'A'

    data class CharGrid(val lines: List<String>) {
        init { lines.checkIsASquare() }
        fun get3x3GridCollection() = Grid3x3Collection(enumerate3x3Grids().map { Grid3x3(it) })

        private fun enumerate3x3Grids(): List<List<String>> {
            val (rows, cols) = Pair(lines.size, lines.first().length)
            check(rows >= 3 || cols >= 3) { "Invalid CharGrid: must be at least 3x3 characters. ($rows, $cols)" }
            // TODO - just use three lines, not 2 loops
            return buildList {
                (0 until rows - 2).forEach { i ->
                    (0 until cols - 2).forEach { j ->
                        val grid =
                            (i..i + 2).map { row ->
                                (j..j + 2).map { col ->
                                    lines[row][col]
                                }
                            }.map { it.joinToString("") }
                        add(grid)
                    }
                }
            }
        }
    }
}
