package aoc.exercises.day7

import aoc.base.AbstractExercise

data class Bag(val color: String, val bags: List<Pair<Int, Bag>>)

class Day7 : AbstractExercise(7) {

    override val inputAsList: List<String>
        get() = readFileAsList()

    val map: Map<String, List<Pair<Int, String>>> = genMap()

    private final fun genMap(): Map<String, List<Pair<Int, String>>> {
        val map: HashMap<String, List<Pair<Int, String>>> = hashMapOf()
        readFileAsList().filter { it.isNotBlank() }.forEach {
            val split = it.split("contain")
            val key = split[0].trim()
            val bags = split[1].replace("no", "0").substringBefore('.').split(',').map { s ->
                val n = s.trim().filter { c -> c.isDigit() }.toInt()
                var b = s.filter { c -> c.isLetter() || c.isWhitespace() }.trim()
                if(!b.endsWith("s")) {
                    b = "${b}s"
                }
                Pair(n, b)
            }
            map[key] = bags
        }
        return map
    }

    fun canHold(bagInput: String, bagToHold: String): Boolean {
        return bagInput == bagToHold || (bagInput != "other bags" && map[bagInput]!!.map { canHold(it.second, bagToHold) }.reduce { acc, b ->  acc || b })
    }

    override fun partOne(): Any? {
        return map.entries.filter { canHold(it.key, "shiny gold bags") }.size - 1
    }

    fun countBags(bagInput: String): Int {
        if(bagInput == "other bags") {
            return 1
        }
        return map[bagInput]!!.fold(0) { acc, i ->
            acc + i.first + i.first * countBags(i.second)
        }
    }

    override fun partTwo(): Any? {
        return countBags("shiny gold bags")
    }
}



fun main() {
    Day7().solve()
}
