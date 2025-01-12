@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
    "ktlint:standard:multiline-expression-wrapping",
)

package day03

import com.github.ajalt.mordant.rendering.TextColors.blue
import com.github.ajalt.mordant.rendering.TextColors.green
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.rendering.TextStyles.bold
import com.github.ajalt.mordant.rendering.TextStyles.italic
import day03.CorruptProgram.Companion.MUL_REGEX
import utils.printSolution
import utils.readInput
import java.util.concurrent.atomic.AtomicInteger

internal fun part2() {
    fun part2(input: String, debug: Boolean = false): Int {
        val cp = CorruptProgram(input)
        if (debug) {
            cp.prettyPrint()
            println(cp.instructions)
        }
        return cp.getResult()
    }

    fun part2(lines: List<String>, debug: Boolean = false) = part2(lines.joinToString(), debug)

    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
    check(part2(part2TestInput) == 48)
    part2(readInput("src/day03/Day03.txt")).printSolution(3, 2)
}

private typealias Instruction = MatchResult
private fun Instruction.isDo() = value == "do()"
private fun Instruction.isDont() = value == "don't()"
private fun Instruction.isMul() = MUL_REGEX.matches(value)
private fun Instruction.mulResult() = getMulValue(value)

fun getMulValue(instruction: String): Int {
    val (x,y) =
        MUL_REGEX
            .matchEntire(instruction)
            ?.destructured ?: throw IllegalArgumentException("Incorrect mul(x,y) instruction: $instruction")
    return x.toInt() * y.toInt()
}

class CorruptProgram(val input: String) {
    val instructionMatches = (DONT_REGEX.findAll(input) + DO_REGEX.findAll(input) + MUL_REGEX.findAll(input))
        .sortedBy { it.range.start }
        .toList()

    fun getResult(): Int {
        val sum = AtomicInteger(0)
        var mulEnabled = true
        instructionMatches.forEach { i ->
            when {
                i.isDo() -> mulEnabled = true
                i.isDont() -> mulEnabled = false
                i.isMul() -> if(mulEnabled) sum.getAndAdd(i.mulResult())
            }
        }
        return sum.toInt()
    }

    val instructions = instructionMatches.map { it.value }
    private val pretty = lazy {
        input
            .replace(DONT_REGEX) { mr -> italic(red(mr.value)) }
            .replace(DO_REGEX) { mr -> italic(blue(mr.value)) }
            .replace(MUL_REGEX) { mr -> bold(green(mr.value)) }
    }

    fun prettyOutput(): String = pretty.value
    fun prettyPrint() = println(prettyOutput())

    companion object {
        val MUL_REGEX = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val DONT_REGEX = """don't\(\)""".toRegex()
        val DO_REGEX = """do\(\)""".toRegex()
    }
}
