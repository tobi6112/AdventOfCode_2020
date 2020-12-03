package aoc.base

import aoc.utils.Color
import aoc.utils.ColoredPrinter
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.lang.Exception
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@State(Scope.Benchmark)
abstract class AbstractExercise(private val day: Int) {

    final val inputAsList = readFileAsList()
    final val inputAsString = readFileAsString()

    private final fun readFileAsList(): List<String> {
        return this::class.java.getResource("/inputs/Day$day").readText().split(System.lineSeparator())
    }

    private final fun readFileAsString(): String {
        return this::class.java.getResource("/inputs/Day$day").readText()
    }

    abstract fun partOne(): Any?

    abstract fun partTwo(): Any?

    final fun solve() {
        ColoredPrinter.println("========= Day $day =========", Color.GREEN)
        printPart(1, this::partOne)
        printPart(2, this::partTwo)
        ColoredPrinter.print(System.lineSeparator())
    }

    private fun printPart(number: Int, method: () -> Any?) {
        ColoredPrinter.print("$number: ")
        try {
            val part = solveAndMeasureTime { method() }
            ColoredPrinter.print("${part.first} ", Color.RED)
            ColoredPrinter.print("(took ${part.second} ms)", Color.YELLOW)
        } catch (e: NotImplementedError) {
            ColoredPrinter.print("not implemented yet", Color.RED)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        ColoredPrinter.print(System.lineSeparator())
    }

    private fun solveAndMeasureTime(e: () -> Any?): Pair<Any?, Double> {
        val result: Any?
        val time = measureNanoTime {
            result = e()
        }
        return Pair(result, time.toDouble().div(1_000_000))
    }

    @Benchmark
    final fun benchmarkPartOne() {
        partOne()
    }

    @Benchmark
    final fun benchmarkPartTwo() {
        partTwo()
    }
}
