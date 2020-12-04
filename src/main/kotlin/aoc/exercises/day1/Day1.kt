package aoc.exercises.day1

import aoc.base.AbstractExercise

class Day1 : AbstractExercise(1) {
    private fun getNumbers(): List<Int> {
        return this.inputAsList.map { it.trim().toInt() }
    }

    override fun partOne(): Int? {
        val sortedNumbers = getNumbers().sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                if (n + n2 == 2020) {
                    return n * n2
                }
            }
        }
        return null
    }

    override fun partTwo(): Int? {
        val sortedNumbers = getNumbers().sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                sortedNumbers.forEach { n3 ->
                    if (n + n2 + n3 == 2020) {
                        return n * n2 * n3
                    }
                }
            }
        }
        return null
    }
}

fun main() {
    val day11 = Day1()
    day11.solve()
}
