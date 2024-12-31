package day03

fun main() {

    val exampleMulInput = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val exampleConditionalInput = $$"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))}"

    val MUL_REGEX = """mul\((\d+),(\d+)\)""".toRegex()
    val DONT_REGEX = """don't\(\)""".toRegex()
    val DO_REGEX = """do\(\)""".toRegex()

    fun getDontInstructions(input: String) = DONT_REGEX.findAll(input).toList()
    fun getDoInstructions(input: String) = DO_REGEX.findAll(input).toList()
    fun getMulInstructions(input: String) = MUL_REGEX.findAll(input).toList()

    fun part2(
        lines: List<String>,
        debug: Boolean = false,
    ): Int {
        val input = lines.joinToString()
        println("::: Input:                $input")
        val dontMatches = getDontInstructions(input)
        val doMatches = getDoInstructions(input)
        val mulMatches = getMulInstructions(input)

        println("::::: don't Matches")
        dontMatches.forEachIndexed { i, mr ->
            println("::: Match $i")
            println(i)
            println(mr)
        }

        println("::::: do Matches")
        println(doMatches)

        println("::::: Mul Matches")
        println(mulMatches)

        return 0
    }

    fun part2(
        input: String,
        debug: Boolean = false,
    ): Int = part2(listOf(input), debug)

//    val part2TestInput = readInput("src/day03/Day03_part2_test.txt")
//    check(part2(part2TestInput, debug = false) == 48)

    //println("::: Part 2 Test Solution: input: ${part2(input)}")
    println("\n\n::: Part 2 Test Solution: input: ${part2(exampleMulInput)}")
    println("\n\n::: Part 2 Test Solution: input: ${part2(exampleConditionalInput)}")

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
