@file:Suppress(
    "ktlint:standard:function-signature",
    "ktlint:standard:class-signature",
    "ktlint:standard:statement-wrapping",
    "unused",
)

package day05

import utils.readInput

fun main() { part1() }

private val testInput = run { readInput("src/day05/Day05_test.txt") }
private val input = run { readInput("src/day05/Day05.txt") }

private fun part1() {
    val (rules, updates) = getRulesAndUpdates(testInput)

    println("::: Updates ${updates.size}")
    val validUpdates = updates.filter {
        it.satisfiesAllRules(rules)
    }
    println("::: Valid Updates ${validUpdates.size}")
    validUpdates.forEach { println(it) }

//    val u = updates.first()
//    val ar = u.getApplicableRules(rules)
//    println("::: Rules ${rules.size}")
//    println("::: Input ${u}")
//    println("::: Applicable Rules ${ar.size}")
//    ar.forEach { println(it) }
//
//    println(u.satisfiesAllRules(ar))

}

typealias Page = List<Long>

private data class Update(val updates: List<Long>) {
    fun getApplicableRules(r: List<Rule>) = r.filter { it.appliesToUpdate(this) }

    fun satisfiesAllRules(r: List<Rule>): Boolean {
        val applicable = getApplicableRules(r)
        applicable.forEach {
            if (!it.appliesToUpdate(this)) return false
        }
        return true
    }

    companion object {
        fun fromString(s: String) = Update(s.split(",").map { it.trim().toLong() })
        fun fromLines(lines: List<String>) = lines.filter { it.contains(",") }.map { Update.fromString(it) }
    }
}

private data class Rule(val a: Long, val b: Long) {
    constructor(vararg ab: Long) : this(ab.first(), ab.last()) {
        check(ab.size == 2) { "Invalid rule.  Rules must consist of 2 page numbers.  Got ${ab.size} instead" }
    }


    fun appliesToUpdate(u: Update) =
        listOf(a,b)
            .all { u.updates.contains(it) }

    fun checkAgainstUpdate(u: Update): Boolean {
        require(u.updates.contains(a) && u.updates.contains(b))
        return u.updates.indexOf(a) < u.updates.indexOf(b)
    }

    companion object {
        fun fromString(s: String): Rule {
            val (a, b) = s.split("|").map { it.toLong() }
            return Rule(a.toLong(), b.toLong())
        }
        fun fromLines(lines: List<String>) = lines.filter { it.contains("|") }.map { Rule.fromString(it) }
    }
}

private fun getRulesAndUpdates(lines: List<String>): Pair<List<Rule>, List<Update>> {
    val (ruleStrings, updateStrings) = testInput.filter { it.isNotEmpty() }.partition { it.contains("|") }
    return Pair(Rule.fromLines(ruleStrings), Update.fromLines(updateStrings))
}
