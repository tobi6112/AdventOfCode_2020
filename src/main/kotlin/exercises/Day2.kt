package exercises

import base.AbstractExercise

class Day2 : AbstractExercise() {
    fun readPasswords(): List<Pair<String, String>> {
        return this.readFile("/inputs/Day2")
                .map {
                    val split = it.split(":")
                    Pair(split[0].trim(), split[1].trim())
                }
    }

    fun createPolicyFromString(policy: String) : (String) -> Boolean {
        val split = policy.split("\\s".toRegex())
        val letter = split[1].toCharArray()[0]
        val minMax = split[0].split("-").map { it.toInt() }

        return { s ->
            val size = s.toCharArray().filter { it == letter }.size
            size >= minMax[0] && size <= minMax[1]
        }
    }

    fun createSecondPolicyFromString(policy: String): (String) -> Boolean {
        val split = policy.split("\\s".toRegex())
        val letter = split[1].toCharArray()[0]
        val pos = split[0].split("-").map { it.toInt() - 1 }

        return {s ->
            s.toCharArray()
                    .filterIndexed {index, c -> (index == pos[0] || index == pos[1]) && c == letter }
                    .size == 1
        }
    }
}

fun main() {
    val day2 = Day2()
    println(day2.readPasswords().filter { day2.createPolicyFromString(it.first)(it.second) }.size)
    println(day2.readPasswords().filter { day2.createSecondPolicyFromString(it.first)(it.second) }.size)
}