package aoc.exercises.day2

import aoc.base.AbstractExerciseTest
import aoc.exercises.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test : AbstractExerciseTest() {

  private val day2 = Day2()

  @Test
  override fun partOne() {
    assertEquals(2, day2.partOne())
  }

  @Test
  override fun partTwo() {
    assertEquals(1, day2.partTwo())
  }
}
