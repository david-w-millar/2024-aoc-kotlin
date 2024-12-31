package day03

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*

fun main() {
    val exampleMulInput         = $$"xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val exampleConditionalInput1 = $$"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))}"

    fun part2(input: String): Int {
        val program = CorruptProgram(input)
        println(program.printSexy())
        println("-------------------------")
        println(input)
        println(program.sortedInstructions.map { it.value })
        println("-------------------------")

        return 0
    }

    fun part2(lines: List<String>) = part2(lines.joinToString())


//    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
//    check(part2(part2TestInput, debug = false) == 48)

    //println("::: Part 2 Test Solution: input: ${part2(input)}")
    println("\n\n::: Part 2 Test Solution: input: ${part2(exampleMulInput)}")
    println("\n\n::: Part 2 Test Solution: input: ${part2(exampleConditionalInput1)}")
    println("\n\n::: Part 2 Test Solution: input: ${part2(exampleConditionalInput1)}")


}

class CorruptProgram(val input: String) {
    val dontMatches = DONT_REGEX.findAll(input).toList()
    val doMatches = DO_REGEX.findAll(input).toList()
    val mulMatches = MUL_REGEX.findAll(input)

    val sortedInstructions: List<MatchResult> =
        dontMatches.toMutableList().let {
            it.addAll(doMatches.toMutableList())
            it.addAll(mulMatches)
            it
        }.sortedBy { it.range.start }

    fun sexyOutput(): String =
        input.replace(DONT_REGEX) { mr -> italic(red(mr.value)) }
            .replace(DO_REGEX) { mr -> italic(blue(mr.value)) }
            .replace(MUL_REGEX) { mr -> bold(green(mr.value)) }

    fun printSexy() = println(sexyOutput())

    companion object {
        val MUL_REGEX = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val DONT_REGEX = """don't\(\)""".toRegex()
        val DO_REGEX = """do\(\)""".toRegex()
    }
}

//    fun getValidInstructions(lines: List<String>): List<String> = MULL_REGEX.findAll(lines.joinToString()).map { it.value }.toList()
//    fun getMultiplicands(instruction: String): Pair<Int,Int> {
//        val (x,y) =
//            mulInstructionRegex
//                .matchEntire(
//                    instruction
//                )?.destructured ?: throw IllegalArgumentException("Incorrect input line $instruction")
//        return Pair(x.toInt(), y.toInt())
//    }
//    fun getMulResult(instruction: String): Int = getMultiplicands(instruction).let { it.first * it.second }
//    fun part1(lines: List<String>): Int = getValidInstructions(lines).sumOf { getMulResult(it) }

//val mulInstructionRegex = """mul\((\d+),(\d+)\)""".toRegex()
//val dontDoBlock = """don't\(\).*do\(\)""".toRegex()
