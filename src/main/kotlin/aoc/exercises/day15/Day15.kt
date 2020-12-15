package aoc.exercises.day15

import aoc.base.AbstractExercise

class Day15 : AbstractExercise(15) {

  override val inputAsList: List<String>
    get() = readFileAsList(",")

  override fun partOne(): Any? {
    return elvesGame(inputAsList.map { it.toLong() }, 2020)
  }

  override fun partTwo(): Any? {
    return elvesGame(inputAsList.map { it.toLong() }, 30000000)
  }

  private fun elvesGame(input: List<Long>, n: Long): Long {
    val said = mutableMapOf<Long, Long?>()
    var i: Long = 0
    var lastNumber: Long = 0
    var tmp: Long
    input.forEach {
      lastNumber = it
      said[lastNumber] = i
      i++
    }
    while (i < n) {
      when(said[lastNumber]) {
        null -> {
          said[lastNumber] = i - 1
          lastNumber = 0
        }
        else -> {
          tmp = i - 1 - said[lastNumber]!!
          said[lastNumber] = i - 1
          lastNumber = tmp
        }
      }
      i++
    }
    return lastNumber
  }
}

fun main() {
    Day15().solve()
}

