package aoc.exercises.day9

import aoc.base.AbstractExercise
import kotlin.Exception

class Day9 : AbstractExercise(9) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  override fun partOne(): Any? {
    val numbers = inputAsList.map { it.toLong() }
    val preamble = arrayListOf<Long>()
    (0..24).forEach {i -> //Init Preamble
      preamble.add(numbers[i])
    }
    numbers.takeLast(numbers.size - 25).forEach {
      if(!isSumOfPreamble(preamble, it)) {
        return it
      }
      preamble.removeFirst()
      preamble.add(it)
    }
    return null
  }

  fun isSumOfPreamble(preamble: List<Long>, number: Number): Boolean {
    preamble.forEach { n ->
      preamble.forEach { m ->
          if(n + m == number) {
            return true
          }
      }
    }
    return false
  }

  override fun partTwo(): Any? {
    val numbers = inputAsList.map { it.toLong() }.toTypedArray()
    val slice = sliceAtSummands(numbers, 375054920L)
    return slice.minOrNull()!! + slice.maxOrNull()!!
  }

  fun sliceAtSummands(numbers: Array<Long>, number: Long): List<Long> {
    var sum = 0L
    var j = 0
    var i = 0
    while( j < numbers.size) {
      if(sum == number) {
        return numbers.slice(i..j)
      }
      if(sum > number) {
        sum -= numbers[i++]
        continue
      }
      sum += numbers[j++]
    }
    throw Exception()
  }
}

fun main() {
    Day9().solve()
}
