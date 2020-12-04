package aoc.exercises.day4

import aoc.base.AbstractExercise

class Day4 : AbstractExercise(4) {

  override val inputAsList: List<String>
    get() = readFileAsList("(${System.lineSeparator()}){2}".toRegex())

    private final val predicates: Map<String, (String) -> Boolean> =
      mapOf(
          Pair("byr") { it.toIntOrNull() in 1920..2002 },
          Pair("iyr") { it.toIntOrNull() in 2010..2020 },
          Pair("eyr") { it.toIntOrNull() in 2020..2030 },
          Pair("hgt") {
            when (it.takeLast(2)) {
              "cm" -> it.take(3).toIntOrNull() in 150..193
              "in" -> it.take(2).toIntOrNull() in 59..76
              else -> false
            }
          },
          Pair("hcl") { it.trim().startsWith("#") && it.trim().length == 7 && it.takeLast(6).toIntOrNull(16) != null },
          Pair("ecl") {
            when (it) {
              "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true
              else -> false
            }
          },
          Pair("pid") { it.trim().length == 9 && it.toIntOrNull() != null })

  override fun partOne(): Any? {
    return inputAsList
        .filter { string ->
          string.replace(System.lineSeparator(), " ")
              .split(" ")
              .map { it.split(":") }
              .filter { predicates.keys.contains(it[0]) }
              .size == predicates.size
        }
        .size
  }

  override fun partTwo(): Any? {
    return inputAsList
        .filter { string ->
          string.replace(System.lineSeparator(), " ")
              .split(" ")
              .map { it.split(":") }
              .filter { predicates.keys.contains(it[0]) }
              .takeIf { it.size == predicates.size }
              ?.fold(true) { acc, s -> acc && (predicates[s[0]]?.let { it1 -> it1(s[1]) } == true) }
              ?: false
        }
        .size
  }
}

fun main() {
    Day4().solve()
}
