package aoc.exercises.day13

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test : AbstractExerciseTest() {

  private val day13 = Day13()

  @Test
  override fun partOne() {
    Assertions.assertEquals(295, day13.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(1068781L, day13.partTwo())
  }
}
