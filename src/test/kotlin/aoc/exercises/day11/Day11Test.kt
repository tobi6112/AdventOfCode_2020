package aoc.exercises.day11

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day11Test : AbstractExerciseTest() {

  private val day11 = Day11()

  @Test
  override fun partOne() {
    Assertions.assertEquals(37, day11.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(26, day11.partTwo())
  }
}
