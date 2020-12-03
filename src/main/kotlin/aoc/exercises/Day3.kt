package aoc.exercises

import aoc.base.AbstractExercise

class Day3 : AbstractExercise(3) {

    override fun partOne(): Any? {
        return countTreesInSlope()
    }

    override fun partTwo(): Any? {
        return countTreesInSlope(right = 1, down =1) * countTreesInSlope(right = 3,down = 1) * countTreesInSlope(right = 5,down = 1) * countTreesInSlope(right = 7,down = 1) * countTreesInSlope(right = 1, down = 2)
    }

    fun countTreesInSlope(inputList: List<String> = this.inputAsList, right: Int = 3, down: Int = 1) :Long {
        return inputList
            .filterIndexed { i, _ ->  i % down == 0}
            .mapIndexed { i, s ->  s[i * right % s.length]}
            .filter { it == '#'}
            .size.toLong()
    }
}

fun main() {
    Day3().solve()
}
