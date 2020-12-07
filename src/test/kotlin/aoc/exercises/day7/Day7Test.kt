package aoc.exercises.day7

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test : AbstractExerciseTest() {

  private val day7 = Day7()

  @Test
  override fun partOne() {
    Assertions.assertEquals(4, day7.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(32, day7.partTwo())
  }
}
