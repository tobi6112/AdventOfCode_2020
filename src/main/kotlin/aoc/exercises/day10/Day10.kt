package aoc.exercises.day10

import aoc.base.AbstractExercise

class Day10 : AbstractExercise(10) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  override fun partOne(): Any? {
    val list = inputAsList.asSequence()
      .map { it.toInt() }
      .sortedDescending()
      .plus(0)
      .plus(deviceRating())
      .sorted()
      .zipWithNext()
      .takeWhile {
        canConnect(it.first, it.second)
      }
    val diffOne = list.count { it.second - it.first == 1 }
    val diffTwo = list.count { it.second - it.first == 3 }

    return diffOne * diffTwo
  }

  override fun partTwo(): Any? {
    val list = inputAsList.map { it.toLong() }.plus(0).sortedDescending()
    val map: MutableMap<Long, Long> = mutableMapOf(list[0] + 3 to 1L)
    list.forEach {
      val n = map[it + 1]  ?: 0L
      val m = map[it + 2]  ?: 0L
      val o = map[it + 3]  ?: 0L
      map[it] = n+m+o
    }
    return map[0L]
  }

  fun deviceRating(): Int = inputAsList.map { it.toInt() }.maxOrNull()!! + 3

  fun canConnect(source: Int, adapter: Int): Boolean = adapter - source in 1..3
}

fun main() {
    Day10().solve()
}
