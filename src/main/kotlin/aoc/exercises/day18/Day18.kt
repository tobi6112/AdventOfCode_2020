package aoc.exercises.day18

import aoc.base.AbstractExercise
import java.util.*


class Day18 : AbstractExercise(18) {

  override val inputAsList: List<String>
    get() = readFileAsList().filter { it.isNotBlank() }

  private fun solve(str: String, priority: (Char) -> Int) : Long {
    val operands: Stack<Long> = Stack()
    val operators: Stack<Char> = Stack()

    str.forEach {
      if(!it.isWhitespace()) {
        when {
          it.isDigit() -> operands.push(it.toString().toLong())
          it == '(' -> operators.push(it)
          it == ')' -> {
            while (!operators.empty() && operators.peek() != ('(')) {
              operands.push(calculate(operands.pop(), operands.pop(), operators.pop()))
            }
            if (!operators.isEmpty()) {
              operators.pop()
            }
          }
          else -> {
            while (!operators.empty() && priority(operators.peek()) >= priority(it)) {
              operands.push(calculate(operands.pop(), operands.pop(), operators.pop()))
            }
            operators.push(it)
          }
        }
      }
    }

    while(!operators.empty()) {
      operands.push(calculate(operands.pop(), operands.pop(), operators.pop()))
    }

    return operands.pop()
  }

  private fun calculate(a: Long, b: Long, o: Char): Long {
    return when(o) {
      '+' -> a.plus(b)
      '*' -> a.times(b)
      else -> throw UnsupportedOperationException()
    }
  }

  override fun partOne(): Any? {
    return inputAsList.map { s ->
      solve(s) {
        when(it) {
          '+', '*'-> 1
          else -> 0
        }
      }
    }.sum()
  }

  override fun partTwo(): Any? {
    return inputAsList.map { s ->
      solve(s) {
        when(it) {
          '+' -> 2
          '*' -> 1
          else -> 0
        }
      }
    }.sum()
  }
}

fun main() {
    Day18().solve()
}
