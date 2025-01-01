package day03

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import day03.CorruptProgram.Companion.MUL_REGEX
import utils.readInput
import java.util.concurrent.atomic.AtomicInteger



fun main() {
    @Suppress("SpellCheckingInspection")
    val exampleMulInput         = $$"xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    @Suppress("SpellCheckingInspection")
    val exampleConditionalInput1 = $$"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))}"

    // TODO: Type Alias?
    fun MatchResult.isDo() = value == "do()"
    fun MatchResult.isDont() = value == "don't()"
    fun MatchResult.isMul() = MUL_REGEX.matches(value)
    fun MatchResult.mulResult() = getMulValue(value)

    fun part2(input: String, debug: Boolean = false): Int {
        val cp = CorruptProgram(input)
        if ( debug ) cp.prettyPrint()
        if ( debug ) println(cp.instructions)

        val sum = AtomicInteger(0)
        var mulEnabled = true
        cp.instructionMatches.forEach { mr ->
            when {
                mr.isDo() -> mulEnabled = true
                mr.isDont() -> mulEnabled = false
                mr.isMul() -> if( mulEnabled ) sum.getAndAdd(mr.mulResult())
            }
        }

        return sum.toInt()
    }

    fun part2(lines: List<String>, debug: Boolean = false) = part2(lines.joinToString(), debug)

    // --- Solve the Problem
    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
    check(part2(part2TestInput) == 48)
    println("::: Part 2 Test Solution: input: ${part2(exampleMulInput, true)}")
    println("::: Part 2 Test Solution: input: ${part2(exampleConditionalInput1, true)}")

    println("\n::: Part 2 Solution: ${part2(readInput("src/day03/Day03.txt"))}")

}

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

    val instructions = instructionMatches.map { it.value }
    private val pretty = lazy {
        input.replace(DONT_REGEX) { mr -> italic(red(mr.value))  }
            .replace(DO_REGEX)    { mr -> italic(blue(mr.value)) }
            .replace(MUL_REGEX)   { mr -> bold(green(mr.value))  }
    }

    fun prettyOutput(): String = pretty.value
    fun prettyPrint() = println(prettyOutput())

    companion object {
        val MUL_REGEX = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val DONT_REGEX = """don't\(\)""".toRegex()
        val DO_REGEX = """do\(\)""".toRegex()
    }

}
