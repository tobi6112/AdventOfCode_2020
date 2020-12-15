package aoc.base

import aoc.utils.Color
import aoc.utils.ColoredPrinter
import com.google.common.base.Stopwatch
import java.lang.Exception
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
abstract class AbstractExercise(private val day: Int) {

  val inputAsList = readFileAsList()
  val inputAsString = readFileAsString()

  final fun readFileAsList(): List<String> {
    return readFileAsList("\\r?\\n".toRegex())
  }

  final fun readFileAsList(split: String): List<String> {
    return readFileAsString().split(split)
  }

  final fun readFileAsList(regex: Regex): List<String> {
    return readFileAsString().split(regex)
  }

  private final fun readFileAsString(): String {
    return this::class.java.getResource("/inputs/Day$day").readText()
  }

  abstract fun partOne(): Any?

  abstract fun partTwo(): Any?

  final fun solve(repetitions: Int = 1) {
    ColoredPrinter.println("========= Day $day =========", Color.GREEN)
    repeat(repetitions) {
      ColoredPrinter.println("Repetition ${it + 1}:", Color.CYAN)
      printPart(1, this::partOne)
      printPart(2, this::partTwo)
    }
    ColoredPrinter.print(System.lineSeparator())
  }

  private fun printPart(number: Int, method: () -> Any?) {
    ColoredPrinter.print("Part $number: ", padding = 2)
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
    val stopwatch = Stopwatch.createStarted()
    val result: Any? = e()
    val time = stopwatch.elapsed().toNanos()
    return Pair(result, time.toDouble().div(1_000_000))
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  final fun benchmarkPartOne() {
    partOne()
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  final fun benchmarkPartTwo() {
    partTwo()
  }
}
