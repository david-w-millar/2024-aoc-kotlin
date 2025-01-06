package day04

import utils.readInput

object Day04Inputs {
    val input = run { readInput("src/day04/Day04.txt") }
    val testInput = run { readInput("src/day04/Day04_test.txt") }
    val testInputMas = run { readInput("src/day04/Day04_test_mas.txt") }

    val testInputA3 = listOf("ABC", "DEF", "GHI")
    val testInputA4 = listOf("ABCD", "EFGH", "IJKL", "MNOP")
    val testInputA6 = listOf("ABCDEF", "GHIJKL", "MNOPQR")
}
