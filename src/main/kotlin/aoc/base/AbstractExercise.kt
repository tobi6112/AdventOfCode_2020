package aoc.base

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
        println("\u001B[32m========= Day $day =========\u001B[0m")
        printPart(1, this::partOne)
        printPart(2, this::partTwo)
    }

    private fun printPart(number: Int, method: () -> Unit) {
        print("\u001B[0mPart $number: \u001B[0m")
        try {
            val part = solveAndMeasureTime { method() }
            print("\u001B[31m${part.first} \u001B[33m(took ${part.second} ms)\u001B[0m")
        } catch (e: NotImplementedError) {
            print("\u001B[31mnot implemented yet\u001B[0m")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        println(System.lineSeparator())
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
