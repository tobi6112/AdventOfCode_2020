package aoc.exercises.day16

import aoc.base.AbstractExercise

class Day16 : AbstractExercise(16) {

    final override val inputAsList: List<String>
        get() = readFileAsList()


    private final val ranges: List<IntRange> = inputAsList.slice(0 until inputAsList.indexOfFirst { it.isBlank() })
          .map { s ->
            val split = s.split(":")
            val rng = split[1].split("or")

            rng.map {
              val splt = it.split("-").map { it.trim().toInt() }
              splt[0]..splt[1]
            }
          }.flatten()

    val ticket = inputAsList[inputAsList.indexOfFirst { it.startsWith("your ticket:") } + 1]
        .split(",")
        .map { it.trim().toInt() }

    val nearbyTickets =
        inputAsList.slice(inputAsList.indexOfFirst { it.startsWith("nearby tickets:") } + 1..inputAsList.indices.last)
          .filter { it.isNotBlank() }
            .map { it.split(",").map { it.trim().toInt() } }

    val validValues = mutableSetOf<Int>()

    init {
      ranges.forEach {
        for (i in it) {
          validValues.add(i)
        }
      }
    }

    override fun partOne(): Any? = nearbyTickets.flatten().filter { !validValues.contains(it) }.sum()

    override fun partTwo(): Any? {
      val filteredTickets = nearbyTickets.filter {
        it.all {
          validValues.contains(it)
        }
      }

      TODO("Lost due to refactoring problems")
    }
}

fun main() {
    Day16().solve()
}
