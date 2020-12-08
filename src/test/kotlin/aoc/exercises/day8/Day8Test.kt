package aoc.exercises.day8

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day8Test : AbstractExerciseTest() {

  private val day8 = Day8()

  @Test
  override fun partOne() {
    Assertions.assertEquals(5, day8.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(8, day8.partTwo())
  }
}
