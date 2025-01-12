@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
)

package day07

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextColors.green
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.rendering.TextStyles.bold
import utils.println
import utils.readInput

@Suppress("ktlint:standard:statement-wrapping")
private fun main() { part1() }

val day = Pair(7,1)
val input = run { readInput("src/day07/Day07_test.txt") }

internal fun part1() {
    println("::: Line")
    val line = input.first()
    println(line)

    val eqValues = CalEqValues.fromString(line).run {
        println()
        prettyPrint()
    }

}

data class CalEqValues(val total: Int, val operands: List<Int>) {
    fun prettyPrint() =
        buildString {
            append(bold(blue("$total")))
            append(" ${bold(green("="))} ")
            append(operands.joinToString(" ${red("?")} ") { bold("$it") })
        }.println()

    companion object {
        fun fromString(s: String): CalEqValues {
            val total = s.substringBefore(":").toInt()
            val operands = s
                .substringAfter(":")
                .trim()
                .split(" ")
                .map(String::toInt)
            return CalEqValues(total, operands)
        }
    }
}


private fun interface Operation {
    fun apply(a: Int, b: Int): Int
}
private val multiplication = Operation { a, b -> a * b }
private val addition = Operation { a, b -> a + b }
private val operations = listOf(multiplication, addition)
