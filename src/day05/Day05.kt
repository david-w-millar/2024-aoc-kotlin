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

    println("::: All Rules (${rules.rules.size})")
    println(rules)

    val u = updates.first()
    val ok = u.satisfiesAllRules(rules)
    println(ok)

    println("\n\n:::::::::::::::::::::\n\n")
    val validUpdates = updates.filter { it.satisfiesAllRules(rules) }
    println("::: Valid Updates (${validUpdates.size})")
    validUpdates.forEach { println(it) }

}

private data class Update(val updates: List<Long>) {
    fun satisfiesAllRules(rules: Rules): Boolean {
        println("::: Update")
        println(this)
        val applicableRules = rules.getApplicableRules(this)

        println("\n::: Applicable Rules (${applicableRules.rules.size})")
        applicableRules.rules.forEach { println(it) }

        return applicableRules.rules.all {
            val check = it.checkAgainstUpdate(this)
            println("::: Checking rule: $it against $this - $check")
            check
        }
    }

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
            .also { println(":: Update $u contains both $a and $b - $it") }

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
    val (ruleStrings, updateStrings) = testInput.filter { it.isNotEmpty() }.partition { it.contains("|") }
    return Pair(Rules.fromLines(ruleStrings), Update.fromLines(updateStrings))
}
