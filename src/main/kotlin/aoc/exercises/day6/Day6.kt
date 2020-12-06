package aoc.exercises.day6

import aoc.base.AbstractExercise

class Day6 : AbstractExercise(6) {

  override val inputAsList: List<String>
    get() = readFileAsList("(\\r?\\n){2}".toRegex())

  override fun partOne(): Any? {
    return inputAsList.map { it.replace("(\\r?\\n|\\s)".toRegex(), "") }
      .map { it.toCharArray().distinct().size }.sum()
  }

  override fun partTwo(): Any? {
    return inputAsList
      .map { s -> s.split("(\\r?\\n|\\s)".toRegex()).map { it.toCharArray().toSet() } }
      .map { it.reduce { acc, intStream ->  acc.intersect(intStream) }.size }
      .sum()
  }
}

fun main() {
    Day6().solve()
}
