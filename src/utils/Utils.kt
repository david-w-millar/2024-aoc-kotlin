@file:Suppress("unused")

package utils

import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Padding
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import utils.UtilConstants.t
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

object UtilConstants {
    val t = Terminal()
}

// ------------------------- IO -------------------------

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path(name).readText().trim().lines()

/** Converts string to md5 hash. */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

fun Any.println() = println(toString())
fun Any.printSolution(
    day: Int,
    part: Int,
) = println("::: Day ${day.toString().padStart(2,'0')} Part $part Solution: $this")

// ------------------------- Collections -------------------------

/** See: https://docs.python.org/3/library/collections.html#collections.Counter */
fun <T> List<T>.elementCounts() = groupingBy { it }.eachCount()

fun List<String>.checkIsASquare() {
    val dimensions = (listOf(size) + map { it.length })
    check(dimensions.distinct().count() == 1) { "Invalid WordSearch dimensions.  Expected a square, got $dimensions" }
}

// ------------------------- Pretty Printing -------------------------

fun List<String>.printLines() = forEach { println(it) }

fun List<String>.prettyPrintAsGrid(title: String = "") {
    val lines = this
    val pretty =
        buildList {
            lines.forEach {
                add(it.toList().joinToString(" "))
            }
        }
    printInPanel(pretty.joinToString("\n"), title)
}

fun List<String>.printInPanel(
    title: String = "",
    asGrid: Boolean = true,
) = if (asGrid) printInPanel(joinToString("\n"), title) else printInPanel(joinToString(" "), title)

fun printInPanel(
    content: String,
    title: String = "",
) {
    t.println(
        Panel(
            content = Text(content),
            title = Text(title),
            titleAlign = TextAlign.CENTER,
            padding = Padding(0, 1, 0, 1),
        )
    )
}

