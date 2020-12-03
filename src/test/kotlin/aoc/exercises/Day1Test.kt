package aoc.exercises

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test : AbstractExerciseTest() {

    private val day1 = Day1()

    @Test
    override fun partOne() {
        assertEquals(day1.partOne(), 514579)
    }

    @Test
    override fun partTwo() {
        assertEquals(day1.partTwo(), 241861950)
    }
}