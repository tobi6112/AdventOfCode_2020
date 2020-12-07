package aoc.exercises.day6

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test : AbstractExerciseTest() {

  private val day6 = Day6()

  @Test
  override fun partOne() {
    Assertions.assertEquals(6530, day6.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(3323, day6.partTwo())
  }
}
