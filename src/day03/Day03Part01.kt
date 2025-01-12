package day03

import utils.Day
import utils.printSolution

fun part1() {
    val day = Day(3, 161)
    val mulInstructionRegex = """mul\((\d+),(\d+)\)""".toRegex()

    fun getValidInstructions(lines: List<String>): List<String> =
        mulInstructionRegex.findAll(lines.joinToString()).map { it.value }.toList()

    fun getMultiplicands(instruction: String): Pair<Int,Int> {
        val (x,y) =
            mulInstructionRegex
                .matchEntire(instruction)
                ?.destructured ?: throw IllegalArgumentException("Incorrect input line $instruction")
        return Pair(x.toInt(), y.toInt())
    }

    fun getMulResult(instruction: String): Int = getMultiplicands(instruction).let { it.first * it.second }
    fun part1(lines: List<String>): Int = getValidInstructions(lines).sumOf { getMulResult(it) }

    check(part1(day.getTestInputLines()) == 161)
    part1(day.getInputLines()).printSolution(3,1)
}
