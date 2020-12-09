package aoc.exercises.day8

import aoc.base.AbstractExercise

class Day8 : AbstractExercise(8) {

  override val inputAsList: List<String>
    get() = readFileAsList()

  val set = hashSetOf<Int>()

  private fun createMap() : MutableMap<Int, Pair<String, Int>> {
    val map : MutableMap<Int, Pair<String, Int>> = mutableMapOf()
    readFileAsList()
      .filter { it.isNotBlank() }
      .map {
      val split = it.split("\\s".toRegex())
      Pair(split[0], split[1].toInt())
    }.forEachIndexed { index, pair ->
      map[index] = pair
    }
    return map
  }

  override fun partOne(): Any? {
    val map = createMap()
    val (_, acc) = process(map)
    return acc
  }

  override fun partTwo(): Any? {
    val map = createMap()
    for(i in 0 until map.entries.size) {
      val value = map[i]
      map[i] = swap(value) //Swap

      //Part One
      val (j, acc) = process(map)

      if(j == map.size) {
        return acc
      }

      map[i] = swap(map[i]) //Swap Back
    }

    throw Exception("Could not repair")
  }

  fun process(map: Map<Int, Pair<String, Int>>): Pair<Int, Int> {
    var i = 0
    var acc = 0
    set.clear()
    while(!set.contains(i)) {
      if(i == map.size) {
        break
      }
      val value = map[i]
      set.add(i)
      when(value!!.first) {
        "nop" -> i++
        "jmp" -> i += value!!.second
        "acc" -> {
          acc += value!!.second
          i++
        }
        else -> throw UnsupportedOperationException()
      }
    }

    return Pair(i, acc)
  }

  fun swap(value: Pair<String, Int>?) : Pair<String, Int> {
    return when(value!!.first) {
      "jmp" -> Pair("nop", value.second)
      "nop" -> Pair("jmp", value.second)
      "acc" -> Pair("acc", value.second)
      else -> throw UnsupportedOperationException()
    }
  }
}

fun main() {
    Day8().solve()
}
