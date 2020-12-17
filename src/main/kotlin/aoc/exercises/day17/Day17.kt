package aoc.exercises.day17

import aoc.base.AbstractExercise
import kotlin.math.pow

private data class Cube(val coords: List<Int>) {
  val dim = coords.size
}

private class Universe(
  cbs: List<Cube>,
  val states: MutableMap<Cube, Boolean> = mutableMapOf(),
  val cubes: MutableMap<Cube, Set<Cube>> = mutableMapOf()
) {

  init {
      cbs.forEach {
        addCube(it)
        setState(it, true)
      }
  }

  fun cycle() {
    val tempStates = states.toMutableMap()
    tempStates.keys.forEach { cube ->
      if (!cubes.containsKey(cube)) {
        addCube(cube)
      }
      val neighbours = cubes[cube]!!
      val activeNeighbours = neighbours.filter { tempStates[it] == true }.size
      if (tempStates[cube]!!) {
        setState(cube, (2..3).contains(activeNeighbours))
      } else {
        setState(cube, activeNeighbours == 3)
      }
    }
  }

  fun addCube(cube: Cube) {
    if (!cubes.containsKey(cube)) {
      if (!states.keys.contains(cube)) {
        states[cube] = false
      }
      val neighbours = getNeighbours(cube)
      cubes[cube] = neighbours
      neighbours.filter { !states.keys.contains(it) }
        .forEach {
          states[it] = false
        }
    }
  }

  fun setState(cube: Cube, state: Boolean) {
    val currentState = states[cube]!!
    if (currentState != state) {
      if (!currentState) {
        addCube(cube)
        states[cube] = true
      } else {
        states[cube] = false
      }
    }
  }

  fun getNeighbours(cube: Cube): Set<Cube> {
    val neighbours = mutableSetOf<Cube>()

    val sums = MutableList(cube.dim) { -1 }
    val divs = (0 until cube.dim).map {
      3.pow(it)
    }
    for(i in 1..3.pow(cube.dim))
    {
      if (!sums.all { it == 0 }) {
        val nCoord = cube.coords.mapIndexed { index, coord ->
          coord + sums[index]
        }
        neighbours.add(Cube(nCoord))
      }
      divs.forEachIndexed { index, j ->
        if (i % j == 0) {
          sums[index] += 1
          if (sums[index] == 2) {
            sums[index] = -1
          }
        }
      }
    }
    return neighbours
  }

  fun countActive() = cubes.keys.filter { states[it] == true }.size

}

fun Int.pow(i: Int) : Int = this.toDouble().pow(i).toInt()

class Day17 : AbstractExercise(17) {

  final override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }


  @ExperimentalStdlibApi
  override fun partOne(): Any? {
    val lstCubes = mutableListOf<Cube>()
    inputAsList.forEachIndexed { y, s ->
      s.forEachIndexed { x, c ->
        if(c == '#') {
          val coords = buildList { //3D
            add(x)
            add(y)
            add(0)
          }
          lstCubes.add(Cube(coords))
        }
      }
    }
    val universe = Universe(lstCubes)

    repeat(6) {
      universe.cycle()
    }
    return universe.countActive()
  }

  @ExperimentalStdlibApi
  override fun partTwo(): Any? {
    val lstCubes = mutableListOf<Cube>()
    inputAsList.forEachIndexed { y, s ->
      s.forEachIndexed { x, c ->
        if(c == '#') {
          val coords = buildList { //4D
            add(x)
            add(y)
            add(0)
            add(0)
          }
          lstCubes.add(Cube(coords))
        }
      }
    }
    val universe = Universe(lstCubes)

    repeat(6) {
      universe.cycle()
    }
    return universe.countActive()
  }
}

fun main() {
    Day17().solve()
}
