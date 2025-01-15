@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
    "unused",
)

package day05

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextColors.green
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.rendering.TextStyles.bold
import utils.printLines
import utils.printSolution
import utils.println
import utils.readInput

fun main() { part1() }

private val testInput = run { readInput("src/day05/Day05_test.txt") }
private val input = run { readInput("src/day05/Day05.txt") }

private fun part1() {
    val inputRegex = """<?a>(\d+)\|<?b>(\d+)""".toRegex()
    testInput.printLines()

    val pair = inputRegex.matchEntire(testInput.first())
    println(pair)

    testInput.filter { it.contains("|") }
        .map {
            println("JAWN")
            val match = inputRegex.find(it)
            match.groups.forEach { println(it) }
        }
}

data class Rule(val a: Long, val b: Long) {

}


