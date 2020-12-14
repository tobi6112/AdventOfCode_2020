package aoc.exercises.day14

import aoc.base.AbstractExercise

class Day14 : AbstractExercise(14) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  override fun partOne(): Any? {
    var mask = ""
    val memory = mutableMapOf<Long, Long>()
    inputAsList.forEach {
      if(it.startsWith("mask")) {
        mask = it.substringAfter("=").trim()
      } else if(it.startsWith("mem")) {
        val index = it.substringAfter("[").substringBefore("]").toLong()
        val value = it.substringAfter("=").trim().toLong()
        memory[index] = applyBitmask(mask, value)
      }
    }
    return memory.values.sum()
  }

  fun applyBitmask(bitmask: String, value: Long): Long {
    var strRepresentation = value.toString(2).padStart(36, '0')
    bitmask.forEachIndexed { index, c ->
      if(c == '1' || c == '0') {
        strRepresentation = strRepresentation.replaceRange(index..index, c.toString())
      }
    }
    return strRepresentation.toLong(2)
  }

  fun applyFloatingBitmask(bitmask: String, address: Long): Array<Long> {
    var strRepresentation = address.toString(2).padStart(36, '0')
    bitmask.forEachIndexed { index, c ->
      if(c == '1' || c == 'X') {
        strRepresentation = strRepresentation.replaceRange(index..index, c.toString())
      }
    }
    val arr = permute(strRepresentation)
    return arr.map { it.toLong(2) }.toTypedArray()
  }

  fun permute(str: String): Array<String> {
    val index = str.indexOf('X')
    if(index == -1) {
      return arrayOf(str)
    }
    return permute(str.replaceFirst('X', '1')).plus(permute(str.replaceFirst('X', '0')))
  }

  override fun partTwo(): Any? {
    var mask = ""
    val memory = mutableMapOf<Long, Long>()
    inputAsList.forEach {
      if(it.startsWith("mask")) {
        mask = it.substringAfter("=").trim()
      } else if(it.startsWith("mem")) {
        val index = it.substringAfter("[").substringBefore("]").toLong()
        val value = it.substringAfter("=").trim().toLong()
        applyFloatingBitmask(mask, index).forEach { i ->
          memory[i] = value
        }
      }
    }
    return memory.values.sum()
  }
}

fun main() {
    Day14().solve()
}
