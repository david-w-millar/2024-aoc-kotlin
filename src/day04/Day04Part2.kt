@file:Suppress(
    "ktlint:standard:statement-wrapping",
    "ktlint:standard:class-signature"
)

package day04

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextStyles.bold
import day04.Grid3x3.CharGrid
import utils.checkIsASquare
import utils.prettyPrintAsGrid

fun main() {

    fun part2() {
        // Input
        println(bold(blue("----- Input -----")))
        val testInput = Day04Inputs.input

        // Get Grids
        val grid = CharGrid(testInput)
        val grids = grid.get3x3GridCollection()

        println("Jawn")
        println(grids.grids[0].getStringRep())

        val valid =
            listOf(
                "MMSS",
                "MSMS",
                "SMSM",
                "SSMM",
            )

        val solution = grids.filterByMiddleA().grids.count { valid.contains(it.getStringRep()) }
        println("------------------")
        println(solution)
        println("------------------")


//        println("-------- Grids (${grids.grids.size}) ------------")
//        val g = grids.grids
//        println("-------- Original Size: ${g.size}")

//        g.filterNot { it.getStringRep().contains('A') }.also { println("::: Without A:      ${it.size}") }
//            .filterNot { it.getStringRep().contains('X') }.also { println("::: Without A or X: ${it.size}") }
//            .filter { it.hasMiddleA() }
//            .filter {
//                it.getStringRep().any {
//                    it == ""
//                }
//            }
//            .forEachIndexed { i, it ->
//                println("\n-- $i--")
//                it.getStringRep().println()
//                it.prettyPrint()
//                println(i + 1)
//            }
    }

//    fun getPerms() {
//        val perms = permute(listOf("M", "A", "S"))
//        println(perms.size)
//        perms.forEach { println(it) }
//    }
//    getPerms()

    part2()
}

data class Grid3x3Collection(val grids: List<Grid3x3>) {
    init { check(grids.isNotEmpty()) { "No grids were provided" } }

    fun filterByMiddleA() = Grid3x3Collection(grids.filter { it.hasMiddleA() }.toList())
    //fun filterByRequiredLetterCounts() = Grid3x3Collection()

    fun getWinners() {
        grids.map { it.getStringRep() }
    }


    fun withoutUselessElements() = grids.map { it.replaceUselessElements() }
    //fun filterByHas2Ms() = Grid3x3Collection(grids.filter { it.lines.joinToString("").findAd })

    fun size() = grids.size

    fun prettyPrint() {
        println(bold(blue("----- Grids (Size: ${grids.size}) -----")))
        grids.forEach { it.prettyPrint() }
    }
}

data class Grid3x3(val lines: List<String>) {
    init {
        check(lines.size == 3) { "Lines gotta be 3, dawg" }
    }

    // Upper Left, Upper Right, Lower Left, Lower Right
    fun ul()  = lines[0][0]
    fun ur() = lines[0][2]
    fun ll()  = lines[2][0]
    fun lr() = lines[2][2]

    fun getStringRep() = listOf(ul(),ur(),ll(),lr()).joinToString("")
    fun reverseStringRep(rep: String) = listOf(ul(),ur(),ll(),lr()).joinToString("")

    fun prettyPrint() = lines.prettyPrintAsGrid()
    fun prettyPrintNoX() = lines.map { it.replace("X", ".") }.prettyPrintAsGrid()
    fun prettyPrintSmall() {
        listOf(
            listOf(ul(), ".", ur()),
            listOf(".", "A", "."),
            listOf(ll(), ".", lr())
        ).map { it.joinToString("") }.prettyPrintAsGrid()
    }


    fun hasMiddleA() = lines.get(1).get(1) == 'A'
    fun replaceUselessElements() =
        Grid3x3(
            buildList {
                val line1 = lines[0].run { listOf(get(0).toString(), ".", get(2)).joinToString(("")) }
                val line2 = lines[1].run { listOf(".", get(1).toString(), ".").joinToString("") }
                val line3 = lines[2].run { listOf(get(2).toString(), ".", get(2).toString()) }

                add(line1.toString())
                add(line2.toString())
                add(line3.toString())
            }.toList()
        )

    data class CharGrid(val lines: List<String>) {
        init {
            lines.checkIsASquare()
        }

        fun get3x3GridCollection() = Grid3x3Collection(enumerate3x3Grids().map { Grid3x3(it) })

        fun enumerate3x3Grids(): List<List<String>> {
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


/** Recursive permute - check for libs that have this built in, this is stupid */
fun <T> permute(list: List<T>): List<List<T>> {
    if (list.size == 1) return listOf(list)
    val permutations = mutableListOf<List<T>>()
    val sub = list[0]
    for (permutation in permute(list.drop(1))) {
        for (i in 0..permutation.size) {
            val newPermutation = permutation.toMutableList()
            newPermutation.add(i, sub)
            permutations.add(newPermutation)
        }
    }
    return permutations
}
