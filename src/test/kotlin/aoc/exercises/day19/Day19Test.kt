package aoc.exercises.day19

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day19Test : AbstractExerciseTest() {

  @ExperimentalStdlibApi
  private val day19 = Day19()

  @ExperimentalStdlibApi
  @Test
  override fun partOne() {
    Assertions.assertEquals(2, day19.partOne())
  }

  @ExperimentalStdlibApi
  @Test
  override fun partTwo() {
    Assertions.assertEquals(12, day19.partTwo())
  }
}
