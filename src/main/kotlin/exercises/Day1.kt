package exercises

import base.AbstractExercise

class Day1 : AbstractExercise(1) {
    private fun getNumbers(): List<Int> {
        return this.inputAsList.map { it.trim().toInt() }
    }

    override fun partOne(): Pair<Int, Int>? {
        val sortedNumbers = getNumbers().sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                if (n + n2 == 2020) {
                    return Pair(n, n2)
                }
            }
        }
        return null
    }

    override fun partTwo(): Triple<Int, Int, Int>? {
        val sortedNumbers = getNumbers().sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                sortedNumbers.forEach { n3 ->
                    if (n + n2 + n3 == 2020) {
                        return Triple(n, n2, n3)
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
