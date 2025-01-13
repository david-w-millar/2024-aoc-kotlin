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

fun header(s: String) = println(bold(blue("::: ${s}")))

internal fun part1() {
    header("Line")
    val line = input.last().also { println(it) }

    header("CalEqValues")
    val calEq = CalEqValues.fromString(line)
    calEq.prettyPrint()
    val opCombos = calEq.getAllOperationCombos().also { println(it) }

}

internal data class CalEq(val eq: CalEqValues, val operations: List<Operation>) {
    fun tryOperators(ops: List<Operation>) {
    }

//   fun applyOperationForOperand(val expected: Int, val current: Int)

}

internal data class CalEqValues(val total: Int, val operands: List<Int>) {
    fun prettyPrint() =
        buildString {
            append(bold(blue("$total")))
            append(" ${bold(green("="))} ")
            append(operands.joinToString(" ${red("?")} ") { bold("$it") })
        }.println()

    fun getAllOperationCombos() = getOperationCombos(operands.size - 1).toSet()

    //fun evaluateWithOperations(ops: )

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

typealias opImpl = (Int, Int) -> (Int)
internal data class Operation(val symbol: String, val eval: opImpl) {
    fun apply(a: Int, b: Int) = eval(a,b)
    override fun toString() = symbol
}
internal val multiplication = Operation("*", { a, b -> a * b })
internal val addition = Operation("+", { a, b -> a + b })
internal val operations = listOf(multiplication, addition)

internal fun getOperationCombos(n: Int) = permutationsWithRepetition(operations, n)


/**
 * Finds all permutations of size n, over an alphabet using recursion / backtracking.
 * TODO: Because we have a binary alphabet now, this could probably be more concise.
 * TODO: Find a library that does this FFS
 * TODO: Even better - find a library that generates De Bruijn Sequences, and use windowing to check validity
 * TODO: Even More better - generate a giant list of De Bruijn Sequences to a file, use that
 */
fun <T> permutationsWithRepetition(
    alphabet: List<T>,
    n: Int,
): List<List<T>> {
    val result = mutableListOf<List<T>>()
    val permutation = mutableListOf<T>()
    fun backtrack(i: Int) {
        if (i == n) {
            result.add(permutation.toList())
            return
        }
        for (element in alphabet) {
            permutation.add(element)
            backtrack(i + 1)
            permutation.removeAt(permutation.size - 1)
        }
    }
    backtrack(0)
    return result.toList()
}
