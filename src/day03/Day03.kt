package day03

import utils.Day
import utils.readInput

fun main() {
    val day = Day(3, 161)

    val instructionRegex = """mul\((\d+),(\d+)\)""".toRegex()

    fun getValidInstructions(lines: List<String>): List<String> {
        return instructionRegex.findAll(lines.joinToString()).map { it.value }.toList()
    }

    fun getMultiplicands(instruction: String) : Pair<Int,Int> {
        val (x,y) =
            instructionRegex
                .matchEntire(
                    instruction
                )?.destructured ?: throw IllegalArgumentException("Incorrect input line $instruction")
        return Pair(x.toInt(), y.toInt())
    }

    fun getMulResult(instruction: String) : Int {
        return getMultiplicands(instruction).let { it.first * it.second }
    }

    fun part1(lines: List<String>): Int {
        return getValidInstructions(lines).sumOf { getMulResult(it) }
    }

    fun part2(lines: List<String>) {
        println(lines)
    }

    check(part1(day.getTestInputLines()) == 161)
    //println("::: Part 1 Solution: " + part1(day.getInputLines()))

    val part2Input = readInput("src/day03/Day03_part2_test.txt")
    println("::: Part 2 Test Solution: " + part2(part2Input))


//    day.run {
//        printWorkingSolutionAfterTest(part1(day.getInputLines()), part1(day.getTestInputLines()))
//        part2("part2 test solution").printWorkingSolutionAfterTest(
//            part2(day.getInputLines()),
//            part2(day.getTestInputLines())
//        )
//    }
}
