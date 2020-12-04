package aoc.exercises.day3

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test : AbstractExerciseTest() {
  private val day3 = Day3()

  @Test
  override fun partOne() {
    Assertions.assertEquals(7L, day3.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(336L, day3.partTwo())
  }
}
