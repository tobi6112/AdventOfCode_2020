package aoc.exercises.day15

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day15Test : AbstractExerciseTest() {

  private val day15 = Day15()

  @Test
  override fun partOne() {
    Assertions.assertEquals(436L, day15.partOne())
  }

  @Test
  override fun partTwo() {
    Assertions.assertEquals(175594L, day15.partTwo())
  }
}
