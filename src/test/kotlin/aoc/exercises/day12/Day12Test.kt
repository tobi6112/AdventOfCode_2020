package aoc.exercises.day12

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test : AbstractExerciseTest() {

  private val day12 = Day12()

  @Test
  override fun partOne() {
    Assertions.assertEquals(25, day12.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(286, day12.partTwo())
  }
}
