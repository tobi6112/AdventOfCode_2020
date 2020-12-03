package aoc.exercises

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test : AbstractExerciseTest() {

    private val day2 = Day2()

    @Test
    override fun partOne() {
        assertEquals(day2.partOne(), 2)
    }

    @Test
    override fun partTwo() {
        assertEquals(day2.partTwo(), 1)
    }
}