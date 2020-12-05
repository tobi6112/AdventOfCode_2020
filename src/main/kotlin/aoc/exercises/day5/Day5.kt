package aoc.exercises.day5

import aoc.base.AbstractExercise
import java.lang.Exception
import kotlin.math.pow

class Day5 : AbstractExercise(5) {

    override val inputAsList: List<String>
        get() = readFileAsList().filter { it.isNotBlank() }

    override fun partOne(): Any? {
        return inputAsList.map {
            getId(it)
        }.maxOrNull()
    }

    override fun partTwo(): Any? {
      val (id1, _) = inputAsList
                  .map { getId(it) }
                  .sorted()
                  .zipWithNext()
                  .find { it.second - it.first == 2 }!!
      return id1 + 1
    }

    fun getId(s: String): Int {
      val (row, column) = decode(s)
        return row * 8 + column
    }

    fun decode(s: String): Pair<Int, Int> = Pair(getRow(s), getColumn(s))

    fun getRow(s: String): Int {
        return s.take(7).decode {
            when (it) {
              'F' -> false
              'B' -> true
                else -> throw Exception()
            }
        }
    }

    fun getColumn(s: String): Int {
        return s.takeLast(3).decode {
            when (it) {
              'L' -> false
              'R' -> true
                else -> throw Exception()
            }
        }
    }
}

fun String.decode(op: (Char) -> Boolean): Int = this.toCharArray()
        .foldIndexed(0) { index, acc, c ->
            when (op(c)) {
              true -> acc + 2.pow(this.length - index - 1)
              false -> acc
            }
        }

fun Int.pow(o: Int) = this.toDouble().pow(o).toInt()

fun main() {
    Day5().solve()
}
