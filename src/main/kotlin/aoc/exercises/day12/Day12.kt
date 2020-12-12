package aoc.exercises.day12

import aoc.base.AbstractExercise
import kotlin.math.abs

enum class Instructions(val letter: Char) {
  NORTH('N'),
  EAST('E'),
  SOUTH('S'),
  WEST('W'),
  LEFT('L'),
  RIGHT('R'),
  FORWARD('F')
}

class Ship(var x: Int, var y: Int, var facingDirection: Instructions, var waypoint: Pair<Int,Int> = Pair(x+10, y+1)) {
  init {
      if (facingDirection != Instructions.NORTH &&
        facingDirection != Instructions.EAST &&
        facingDirection != Instructions.SOUTH &&
        facingDirection != Instructions.WEST) {
        throw Exception()
      }
  }
}

class Day12 : AbstractExercise(12) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  override fun partOne(): Any? {
    val ship = Ship(0,0,Instructions.EAST)

    inputAsList.forEach { s ->
      val letter: Char = s[0]
      val amount: Int = s.substringAfter(letter).toInt()
      val instruction = Instructions.values().first { it.letter == letter }

      moveShip(instruction, amount, ship)
    }

    return abs(ship.x) + abs(ship.y)
  }

  fun moveShip(instruction: Instructions, amount: Int, ship: Ship) {
    when(instruction) {
      Instructions.NORTH -> {
        ship.y += amount
      }
      Instructions.EAST -> {
        ship.x += amount
      }
      Instructions.SOUTH -> {
        ship.y -= amount
      }
      Instructions.WEST -> {
        ship.x -= amount
      }
      Instructions.LEFT -> {
        val newIndex = Math.floorMod(ship.facingDirection.ordinal - (amount / 90), 4)
        ship.facingDirection = Instructions.values()[newIndex]
      }
      Instructions.RIGHT -> {
        val newIndex = Math.floorMod(ship.facingDirection.ordinal + (amount / 90), 4)
        ship.facingDirection = Instructions.values()[newIndex]
      }
      Instructions.FORWARD -> {
        moveShip(ship.facingDirection, amount, ship)
      }
    }
  }

  fun moveWaypoint(instruction: Instructions, amount: Int, ship: Ship) {
    when(instruction) {
      Instructions.NORTH -> {
        ship.waypoint = ship.waypoint.first to ship.waypoint.second + amount
      }
      Instructions.EAST -> {
        ship.waypoint = ship.waypoint.first + amount to ship.waypoint.second
      }
      Instructions.SOUTH -> {
        ship.waypoint = ship.waypoint.first to ship.waypoint.second - amount
      }
      Instructions.WEST -> {
        ship.waypoint = ship.waypoint.first - amount to ship.waypoint.second
      }
      Instructions.LEFT -> {
        val turn = Math.floorMod(amount / 90, 4)
        repeat(turn) {
          val (x,y) = ship.waypoint
          ship.waypoint = y * -1 to x
        }
      }
      Instructions.RIGHT -> {
        val turn = Math.floorMod(amount / 90, 4)
        repeat(turn) {
          val (x,y) = ship.waypoint
          ship.waypoint = y to -1 * x
        }
      }
      Instructions.FORWARD -> {
        ship.x += ship.waypoint.first * amount
        ship.y += ship.waypoint.second * amount
      }
    }
  }

  override fun partTwo(): Any? {
    val ship = Ship(0,0,Instructions.EAST)

    inputAsList.forEach { s ->
      val letter: Char = s[0]
      val amount: Int = s.substringAfter(letter).toInt()
      val instruction = Instructions.values().first { it.letter == letter }

      moveWaypoint(instruction, amount, ship)
    }

    return abs(ship.x) + abs(ship.y)
  }
}

fun main() {
    Day12().solve()
}
