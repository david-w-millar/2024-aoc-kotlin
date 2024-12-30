package day03

import utils.readInput

fun main() {

    val exampleInput = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val input = $$"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))}"

    val MUL_REGEX = """mul\((\d+),(\d+)\)""".toRegex()
    val DONT_REGEX = """don't\(\)""".toRegex()
    val DO_REGEX = """do\(\)""".toRegex()

    fun part2(
        lines: List<String>,
        debug: Boolean = false,
    ): Int {
        val input = lines.joinToString()
        println("::: Input:                $input")

        val dontMatches = DONT_REGEX.findAll(input)
        val doMatches = DO_REGEX.findAll(input)
        val mulMatches = MUL_REGEX.findAll(input)

        println(mulMatches)
        println(dontMatches)
        println(doMatches)
        println(mulMatches)

        return 0
    }

    fun part2(
        input: String,
        debug: Boolean = false,
    ): Int = part2(listOf(input), debug)

//    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
//    check(part2(part2TestInput, debug = false) == 48)

    println("::: Part 2 Test Solution: input: ${part2(input)}")
    println("::: Part 2 Test Solution: input: ${part2(exampleInput)}")

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
