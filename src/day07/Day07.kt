@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
    "unused",
)

package day07

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextColors.green
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.rendering.TextStyles.bold
import utils.printSolution
import utils.println
import utils.readInput

fun main() {
    part1()
    part2()
}

private val testInput = run { readInput("src/day07/Day07_test.txt") }
private val input = run { readInput("src/day07/Day07.txt") }

private fun part1() = solveWithOps(listOf(addition, multiplication)).printSolution(7,1)
private fun part2() = solveWithOps(listOf(addition, multiplication, concatenation)).printSolution(7,2)

private fun solveWithOps(ops: List<Operation>) =
    input
        .map { CalEqValues.fromString(it, ops) }
        .filter { it.equationIsSolvable() }
        .sumOf { it.total }

private data class CalEqValues(
    val total: Long,
    val operands: List<Long>,
    val ops: List<Operation>,
) {
    val opCombos = run { getOperationCombos(operands.size - 1, ops) }

    fun equationIsSolvable(): Boolean {
        // TODO: shorten with functional
        for(c in opCombos) {
            if (tryCombo(operands, c) == total) return true
        }
        return false
    }

    fun tryCombo(
        operands: List<Long>,
        ops: List<Operation>,
    ): Long {
        // TODO: Check sizes
        if (ops.isEmpty()) return operands.first()

        val (a, b) = operands.take(2)
        val op = ops.first()

        val opResult = op.compute(a,b)

        val newOperands = listOf(opResult).plus(operands.slice(IntRange(2, operands.size - 1)))
        val newOps = ops.slice(IntRange(1, ops.size - 1))

        return tryCombo(newOperands, newOps)
    }

    fun prettyPrint() =
        buildString {
            append(bold(blue("$total")))
            append(" ${bold(green("="))} ")
            append(operands.joinToString(" ${red("?")} ") { bold("$it") })
        }.println()

    companion object {
        fun fromString(s: String, ops: List<Operation>): CalEqValues {
            val total = s.substringBefore(":").toLong()
            val operands =
                s
                    .substringAfter(":")
                    .trim()
                    .split(" ")
                    .map(String::toLong)
            return CalEqValues(total, operands, ops)
        }
    }
}

private typealias opImpl = (Long, Long) -> (Long)
private data class Operation(val symbol: String, val eval: opImpl) {
    fun compute(a: Long, b: Long) = eval(a,b)
    override fun toString() = symbol
}
private val multiplication = Operation("*", { a, b -> a * b })
private val addition = Operation("+", { a, b -> a + b })
private val concatenation = Operation("+", { a, b -> "$a$b".toLong() })

private fun getOperationCombos(n: Int, ops: List<Operation>) = permutationsWithRepetition(ops, n).toSet()

/**
 * Finds all permutations of size n, over an alphabet using recursion / backtracking.
 * TODO: use a sequence
 * TODO: Because we have a binary alphabet now, this could probably be more concise.
 * TODO: Find a library that does this FFS
 * TODO: Even better - find a library that generates De Bruijn Sequences, and use windowing to check validity
 * TODO: Even More better - generate a giant list of De Bruijn Sequences to a file, use that
 */
private fun <T> permutationsWithRepetition(
    alphabet: List<T>,
    n: Int,
): List<List<T>> {
    val result = mutableListOf<List<T>>()
    val permutation = mutableListOf<T>()
    fun backtrack(i: Long) {
        if (i == n.toLong()) {
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
