package aoc.exercises

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test : AbstractExerciseTest() {
    private val day3 = Day3()

    @Test
    override fun partOne() {
        Assertions.assertEquals(day3.partOne(), 7L)
    }

    @Test
    override fun partTwo() {
        Assertions.assertEquals(day3.partTwo(), 336L)
    }
}