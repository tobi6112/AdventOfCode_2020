package base

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State


abstract class AbstractExercise(private val day: Int) {

    val inputAsList = readFileAsList()
    val inputAsString = readFileAsString()

    private final fun readFileAsList(): List<String> {
        return this::class.java.getResource("/inputs/Day$day").readText().split(System.lineSeparator())
    }

    private final fun readFileAsString(): String {
        return this::class.java.getResource("/inputs/Day$day").readText()
    }

    abstract fun partOne(): Any?

    abstract fun partTwo(): Any?

    open fun solve() {
        println("========= Day $day =========")
        println("Part 1: ${partOne()}")
        println("Part 2: ${partTwo()}")
    }

    @Benchmark
    fun benchmarkPartOne() {
        partOne()
    }

    @Benchmark
    fun benchmarkPartTwo() {
        partTwo()
    }
}
