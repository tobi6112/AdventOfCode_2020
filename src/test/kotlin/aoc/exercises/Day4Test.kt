package aoc.exercises

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day4Test : AbstractExerciseTest() {

  val day4 = Day4()

  @Test
  override fun partOne() {
    Assertions.assertEquals(8, day4.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(4, day4.partTwo())
  }
}
