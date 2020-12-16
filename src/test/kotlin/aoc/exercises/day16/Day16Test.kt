package aoc.exercises.day16

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day16Test : AbstractExerciseTest() {

  private val day16 = Day16()

  @ExperimentalStdlibApi
  @Test
  override fun partOne() {
    Assertions.assertEquals(71, day16.partOne())
  }

  @Test
  override fun partTwo() {
    //Assertions.assertEquals(, day16.partTwo())
  }
}
