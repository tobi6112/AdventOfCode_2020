package aoc.exercises.day17

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day17Test : AbstractExerciseTest() {

  private val day17 = Day17()

  @ExperimentalStdlibApi
  @Test
  override fun partOne() {
    Assertions.assertEquals(112, day17.partOne())
  }

  @ExperimentalStdlibApi
  @Test
  override fun partTwo() {
    Assertions.assertEquals(848, day17.partTwo())
  }
}
