package aoc.base

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.lang.Exception
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
        print("\u001B[0mPart 1: ")
        try {
            val partOne = solveAndMeasureTime { partOne() }
            print("\u001B[31m${partOne.first} \u001B[33m(took ${partOne.second} ms)\u001B[0m")
        } catch (e: NotImplementedError) {
            print("\u001B[31mnot implemented yet\u001B[0m")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        print(System.lineSeparator())

        print("\u001B[0mPart 2: \u001B[0m")
        try {
            val partTwo = solveAndMeasureTime { partTwo() }
            print("\u001B[31m${partTwo.first} \u001B[33m(took ${partTwo.second} ms)\u001B[0m")
        } catch (e: NotImplementedError) {
            print("\u001B[31mnot implemented yet\u001B[0m")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        println(System.lineSeparator())
    }

    private fun solveAndMeasureTime(e: () -> Any?): Pair<Any?, Long> {
        val result: Any?
        val time = measureTimeMillis {
            result = e()
        }
        return Pair(result, time)
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
