package aoc.exercises.day9

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test : AbstractExerciseTest() {

  private val day9 = Day9()

  @Test
  override fun partOne() {
    Assertions.assertEquals(375054920, day9.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(54142584, day9.partTwo())
  }
}
