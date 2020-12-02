package exercises

import base.AbstractExercise

class Day1 : AbstractExercise() {
    fun getNumbers(): List<Int> {
        return this.readFile("/inputs/Day1").map { it.trim().toInt() }
    }

    fun getNumbersThatSumTo2020(numbers: List<Int>): Pair<Int, Int>? {
        val sortedNumbers = numbers.sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                if (n + n2 == 2020) {
                    return Pair(n, n2)
                }
            }
        }
        return null
    }

    fun getThreeNumbersThatSumTo2020(numbers: List<Int>) : Triple<Int, Int, Int>? {
        val sortedNumbers = numbers.sorted()
        sortedNumbers.forEach { n ->
            sortedNumbers.forEach { n2 ->
                sortedNumbers.forEach {n3 ->
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
    val pair = day11.getNumbersThatSumTo2020(day11.getNumbers())
    val triple = day11.getThreeNumbersThatSumTo2020(day11.getNumbers())
    if (pair != null) {
        println(pair.first * pair.second)
    }
    if (triple != null) {
        println(triple.first * triple.second * triple.third)
    }
}