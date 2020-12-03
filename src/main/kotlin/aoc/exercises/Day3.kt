package aoc.exercises

import aoc.base.AbstractExercise

class Day3 : AbstractExercise(3) {

    override fun partOne(): Any? {
        return countTreesInSlope()
    }

    override fun partTwo(): Any? {
        return countTreesInSlope(1, 1) * countTreesInSlope(3,1) * countTreesInSlope(5,1) * countTreesInSlope(7,1) * countTreesInSlope(1, 2)
    }

    fun countTreesInSlope(right: Int = 3, down: Int = 1) :Long {
        return this.inputAsList
            .filterIndexed { index, _ ->  index % down == 0}
            .mapIndexed { index, string ->  string[index * right % string.length]}
            .filter { it == '#'}
            .size.toLong()
    }
}

fun main() {
    Day3().solve()
}
