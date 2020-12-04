package aoc.exercises

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test : AbstractExerciseTest() {

  private val day1 = Day1()

  @Test
  override fun partOne() {
    assertEquals(514579, day1.partOne())
  }

  @Test
  override fun partTwo() {
    assertEquals(241861950, day1.partTwo())
  }
}
