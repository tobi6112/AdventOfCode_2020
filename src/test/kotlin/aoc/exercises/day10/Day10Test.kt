package aoc.exercises.day10

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test : AbstractExerciseTest() {

  private val day10 = Day10()

  @Test
  override fun partOne() {
    Assertions.assertEquals(22*10, day10.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(19208L, day10.partTwo())
  }
}
