@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
    "ktlint:standard:statement-wrapping",
    "unused",
)

package day05

import utils.printSolution
import utils.readInput
import kotlin.Long

private val testInput = run { readInput("src/day05/Day05_test.txt") }
private val input = run { readInput("src/day05/Day05.txt") }

fun main() {
    check(part1(testInput) == 143L)
    part1(input).printSolution(5,1)
    //check(part2(testInput) == 123L)
    part2(testInput).printSolution(5,2)
}


private fun part2(input: List<String>): Long {
    val (rules, updates) = getRulesAndUpdates(input)
    val invalidUpdates = updates.filter { ! it.satisfiesAllRules(rules) }
    invalidUpdates.forEach { println(it) }


    return 0L
    //part2(input).printSolution(5,1)
}

private fun part1(input: List<String>): Long {
    val (rules, updates) = getRulesAndUpdates(input)
    val validUpdates = updates.filter { it.satisfiesAllRules(rules) }
    val sum = validUpdates.sumOf { it.getMiddlePage() }
    return sum
}

private data class Update(val updates: List<Long>) {
    fun satisfiesAllRules(rules: Rules): Boolean {
        val applicableRules = rules.getApplicableRules(this)
        return applicableRules.rules.all { it.checkAgainstUpdate(this) }
    }
    fun getMiddlePage() = updates[updates.size / 2]
    override fun toString() = updates.joinToString()

    companion object {
        fun fromString(s: String) = Update(s.split(",").map { it.trim().toLong() })
        fun fromLines(lines: List<String>) = lines.filter { it.contains(",") }.map { Update.fromString(it) }
    }
}

private data class Rules(val rules: List<Rule>) {
    fun getApplicableRules(u: Update) = Rules(rules.filter { it.appliesToUpdate(u) })

    override fun toString() =
        buildString {
            rules.forEach {
                appendLine(it.toString())
            }
        }

    companion object {
        fun fromLines(lines: List<String>) = Rules(lines.filter { it.contains("|") }.map { Rule.fromString(it) })
    }
}

private data class Rule(val a: Long, val b: Long) {
    constructor(vararg ab: Long) : this(ab.first(), ab.last()) {
        check(ab.size == 2) { "Invalid rule.  Rules must consist of 2 page numbers.  Got ${ab.size} instead" }
    }

    fun appliesToUpdate(u: Update) =
        u.updates
            .containsAll(listOf(a,b))

    override fun toString() = "$a|$b"

    fun checkAgainstUpdate(u: Update): Boolean {
        require(u.updates.contains(a) && u.updates.contains(b))
        return u.updates.indexOf(a) < u.updates.indexOf(b)
    }

    companion object {
        fun fromString(s: String): Rule {
            val (a, b) = s.split("|").map { it.toLong() }
            return Rule(a.toLong(), b.toLong())
        }
    }
}

private fun getRulesAndUpdates(lines: List<String>): Pair<Rules, List<Update>> {
    val (ruleStrings, updateStrings) = lines.filter { it.isNotEmpty() }.partition { it.contains("|") }
    return Pair(Rules.fromLines(ruleStrings), Update.fromLines(updateStrings))
}
