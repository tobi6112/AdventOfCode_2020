package aoc.exercises.day14

import aoc.base.AbstractExerciseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test : AbstractExerciseTest() {

  private val day14 = Day14()

  @Test
  override fun partOne() {
    Assertions.assertEquals(11612740949946L, day14.partOne())
  }

  @Test
  override fun partTwo() {
    //TODO("not yet implemented")
    Assertions.assertEquals(3394509207186L, day14.partTwo())
  }
}
