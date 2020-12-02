package exercises

import base.AbstractExercise

abstract class Policy {
    fun checkPw(password: String, p: Triple<Char, Int, Int>) = checkPw(password, p.first, p.second, p.third)
    abstract fun checkPw(password: String, c: Char, i: Int, j: Int): Boolean
}

class FirstPolicy : Policy() {
    override fun checkPw(password: String, c: Char, i: Int, j: Int): Boolean {
        val size = password.toCharArray().filter { it == c }.size
        return size in i..j
    }
}

class SecondPolicy : Policy() {
    override fun checkPw(password: String, c: Char, i: Int, j: Int): Boolean {
        return password.toCharArray().filterIndexed { index, ch -> (index == i || index == j) && ch == c }.size == 1
    }
}

class Day2 : AbstractExercise() {
    private fun readPasswords(): List<Pair<String, String>> {
        return this.readFile("/inputs/Day2")
                .map {
                    val split = it.split(":")
                    Pair(split[0].trim(), split[1].trim())
                }
    }

    private fun getParameters(policy: String): Triple<Char, Int, Int> {
        val split = policy.split("\\s".toRegex())
        val letter = split[1].toCharArray()[0]
        val numbers = split[0].split("-").map { it.toInt() }
        return Triple(letter, numbers[0], numbers[1])
    }

    override fun solve() {
        println(this.readPasswords().filter { FirstPolicy().checkPw(it.second, getParameters(it.first)) }.size)
        println(this.readPasswords().filter { SecondPolicy().checkPw(it.second, getParameters(it.first)) }.size)
    }
}

fun main() {
    val day2 = Day2()
    day2.solve()
}
