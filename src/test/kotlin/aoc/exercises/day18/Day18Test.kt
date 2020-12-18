package aoc.exercises.day18

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day18Test : AbstractExerciseTest() {

  private val day18 = Day18()

  @Test
  override fun partOne() {
    Assertions.assertEquals(7293529867931L, day18.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(60807587180737L, day18.partTwo())
  }
}
