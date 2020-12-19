package aoc.exercises.day19

import aoc.base.AbstractExercise

private sealed class RuleDefinition {
    class ReferenceRule(val key: Int) : RuleDefinition()
    class ExplicitRule(val string: String) : RuleDefinition()
}

@ExperimentalStdlibApi
class Day19 : AbstractExercise(19) {

    final override val inputAsList: List<String>
        get() = readFileAsList()

    private val rules: Map<Int, List<List<RuleDefinition>>> = buildMap {
        for (line in inputAsList) {
            if (line.isEmpty()) {
                break
            } else {
                val (key, value) = line.toRule()
                this[key] = value
            }
        }
    }
    private val messages: List<String> =
        inputAsList.slice(inputAsList.indexOfFirst { it.isBlank() }..inputAsList.indices.last)
            .filter { it.isNotBlank() }

    private fun String.toRule(): Pair<Int, List<List<RuleDefinition>>> {
        val (key, value) = split(":")
        return Pair(key.toInt(), value.split('|').map { s ->
            s.split("\\s".toRegex()).filter { it.isNotBlank() }.map { item ->
                when {
                    item.trim().startsWith("\"") && item.trim()
                        .endsWith("\"") -> RuleDefinition.ExplicitRule(item.substringAfter("\"").substringBefore("\""))
                    else -> RuleDefinition.ReferenceRule(item.toInt())
                }
            }.toList()
        }.toList())
    }

    private fun getRegex(key: Int, map: Map<Int, List<List<RuleDefinition>>>): String {
        val value = map[key] ?: throw Exception()
        return "(?:${
            value.joinToString("|") {
                it.joinToString("") { item ->
                    when (item) {
                        is RuleDefinition.ReferenceRule -> getRegex(item.key, map)
                        is RuleDefinition.ExplicitRule -> item.string
                    }
                }
            }
        })"
    }

    override fun partOne(): Any? {
        val pattern = getRegex(0, rules).toRegex()
        return messages.filter { pattern.matches(it) }.size
    }

    override fun partTwo(): Any? {
        val rules = rules.mapValues { (_, list) ->
            list.map {
                it.reversed().map { item ->
                    when (item) {
                        is RuleDefinition.ExplicitRule -> RuleDefinition.ExplicitRule(item.string.reversed())
                        is RuleDefinition.ReferenceRule -> RuleDefinition.ReferenceRule(item.key)
                    }
                }
            }
        }
        val regexOfRule31 = getRegex(31, rules)
        val regexOfRule42 = getRegex(42, rules)
        val n = messages.maxOfOrNull { it.length } ?: throw Exception()
        val regex = (1..(n - 1) / 2).joinToString("|") { i ->
            "(?:$regexOfRule31){$i}(?:$regexOfRule42){${i + 1},}"
        }.toRegex()
        return messages.filter { it.reversed().matches(regex) }.size
    }
}

@ExperimentalStdlibApi
fun main() {
    Day19().solve()
}
