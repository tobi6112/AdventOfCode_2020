package aoc.exercises.day5

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test : AbstractExerciseTest() {

  private val day5 = Day5()

  @Test
  override fun partOne() {
    Assertions.assertEquals(926, day5.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(657, day5.partTwo())
    //Assertions.assertEquals(0, day5.partTwo())
  }
}
