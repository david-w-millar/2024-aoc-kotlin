package day03

import utils.Day
import utils.readInput

fun main() {
    val day = Day(3, 161)

    val mulInstructionRegex = """mul\((\d+),(\d+)\)""".toRegex()

    fun getValidInstructions(lines: List<String>): List<String> =
        mulInstructionRegex.findAll(lines.joinToString()).map { it.value }.toList()

    fun getMultiplicands(instruction: String): Pair<Int,Int> {
        val (x,y) =
            mulInstructionRegex
                .matchEntire(
                    instruction
                )?.destructured ?: throw IllegalArgumentException("Incorrect input line $instruction")
        return Pair(x.toInt(), y.toInt())
    }

    fun getMulResult(instruction: String): Int = getMultiplicands(instruction).let { it.first * it.second }

    fun part1(lines: List<String>): Int = getValidInstructions(lines).sumOf { getMulResult(it) }

    //val mulInstructionRegex = """mul\((\d+),(\d+)\)""".toRegex()
    val dontDoBlock = """don't\(\).*do\(\)""".toRegex()

    fun part2(
        lines: List<String>,
        debug: Boolean = false,
    ): Int {
        val input = lines.joinToString()
        println("::: Input:                $input")
        val split = input.split(dontDoBlock)
        if (debug) split.forEachIndexed { i, s -> println("::: $i : $s") }

        val withoutDontBlocks = split.joinToString()
        println("::: Without Don't Blocks: $withoutDontBlocks")
        val validInstructions = getValidInstructions(listOf(withoutDontBlocks))
        return validInstructions.sumOf { getMulResult(it) }
    }

    fun part2(
        input: String,
        debug: Boolean = false,
    ): Int = part2(listOf(input), debug)

    check(part1(day.getTestInputLines()) == 161)
    println("::: Part 1 Solution: " + part1(day.getInputLines()))

    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
    check(part2(part2TestInput, debug = false) == 48)
    println("::: Part 2 Test Solution: " + part2(part2TestInput))
    println(MulExamples.example2)
    println(MulExamples.example_8)

    val part2Solution = part2(day.getInputLines())
    println("::: Part 2 Solution: $part2Solution")
    println(part2(day.getInputLines().joinToString("")))

}
