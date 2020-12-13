package aoc.exercises.day13

import aoc.base.AbstractExercise


class Day13 : AbstractExercise(13) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  override fun partOne(): Any? {
    val timestamp = inputAsList[0].toInt()
    val busses = inputAsList[1].split(',').filter { it != "x" }.map { it.toInt() }

    val min = busses.minByOrNull {
      it - (timestamp % it)
    }!!

    return min * (min - (timestamp % min))
  }

  override fun partTwo(): Any? {
    val offs = mutableListOf<Long>()
    val busses = mutableListOf<Long>()
    var i = 0
    inputAsList[1].split(',').forEachIndexed { i, l ->
      if(l != "x") {
        busses.add(l.toLong())
        offs.add(l.toLong() - i)
      }
    }
    return chineseRemainder(busses.toTypedArray(), offs.toTypedArray())
  }

}

//TODO Refactor: Algorithm from https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
fun Long.multInv(b: Long): Long {
  if (b == 1L) return 1
  var aa = this
  var bb = b
  var x0 = 0L
  var x1 = 1L
  while (aa > 1) {
    val q = aa / bb
    var t = bb
    bb = aa % bb
    aa = t
    t = x0
    x0 = x1 - q * x0
    x1 = t
  }
  if (x1 < 0) x1 += b
  return x1
}

fun chineseRemainder(n: Array<Long>, a: Array<Long>): Long {
  val prod = n.fold(1L) { acc, i -> acc * i }
  var sum = 0L
  for (i in n.indices) {
    val p = prod / n[i]
    sum += a[i] * p.multInv(n[i]) * p
  }
  return sum % prod
}

fun main() {
    Day13().solve()
}
