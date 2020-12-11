package aoc.exercises.day11

import aoc.base.AbstractExercise

class Day11 : AbstractExercise(11) {

    override val inputAsList: List<String>
        get() = readFileAsList()

    fun genArray(list: List<String>): Array<CharArray> {
        return list.filter { it.isNotBlank() }
            .map { it.toCharArray() }
            .toTypedArray()
    }

    override fun partOne(): Any? {
        val field = genArray(inputAsList)
        do {
            val cpyField = deepCopy(field)
            changeState(field)
        } while (!field.contentDeepEquals(cpyField))

        return field.map { chars ->
            chars.count { it == '#' }
        }.sum()
    }

    fun printField(arr: Array<CharArray>) {
        arr.forEach { chars ->
            chars.forEach {
                print(it)
            }
            print("\n")
        }
        print("\n")
    }

    fun changeState(arr: Array<CharArray>): Array<CharArray> {
        val cpyArray = deepCopy(arr)
        cpyArray.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, _ ->
                when (cpyArray[i][j]) {
                    'L' -> {
                        val values = getAdjacenceValues(cpyArray, i to j).distinct()
                        if (values.isNotEmpty() && !values.contains('#')) {
                            arr[i][j] = '#'
                        }
                    }
                    '#' -> {
                        val values = getAdjacenceValues(cpyArray, i to j)
                        if (values.count { it == '#' } >= 4) {
                            arr[i][j] = 'L'
                        }
                    }
                }
            }
        }

        return arr
    }

    fun deepCopy(arr: Array<CharArray>) : Array<CharArray> {
        return arr.copyOf().map {
            it.copyOf()
        }.toTypedArray()
    }

    fun getAdjacenceValues(arr: Array<CharArray>, coord: Pair<Int, Int>): Array<Char> {
        val (x, y) = coord
        var array = arrayOf<Char>()
        for (i in -1..1) {
            for (j in -1..1) {
                if (!((i == 0) && (j == 0))) {
                    if (x + i >= 0 && y + j >= 0 && x + i < (arr[0].size) && y + j < (arr.size)) {
                        array = array.plus(arr[x + i][y + j])
                    }
                }
            }
        }
        return array
    }

    override fun partTwo(): Any? {
        val field = genArray(inputAsList)

        do {
            val cpyField = deepCopy(field)
            changeStatePartTwo(field)
        } while (!field.contentDeepEquals(cpyField))

        return field.map { chars ->
            chars.count { it == '#' }
        }.sum()
    }

    fun changeStatePartTwo(arr: Array<CharArray>): Array<CharArray> {
        val cpyArray = deepCopy(arr)
        cpyArray.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, _ ->
                when (cpyArray[i][j]) {
                    'L' -> {
                        val values = getFirstVisibleSeats(cpyArray, i to j).distinct()
                        if (values.isNotEmpty() && !values.contains('#')) {
                            arr[i][j] = '#'
                        }
                    }
                    '#' -> {
                        val values = getFirstVisibleSeats(cpyArray, i to j)
                        if (values.count { it == '#' } >= 5) {
                            arr[i][j] = 'L'
                        }
                    }
                }
            }
        }

        return arr
    }

    fun getFirstVisibleSeats(arr: Array<CharArray>, coord: Pair<Int, Int>) : Array<Char> {
        val (x, y) = coord
        var array = arrayOf<Char>()
            for(i in -1..1) {
                for(j in -1..1) {
                    if (!((i == 0) && (j == 0))) {
                        var x1 = x + i
                        var y1 = y + j
                        while (x1 >= 0 && y1 >= 0 && x1 < (arr[0].size) && y1 < (arr.size)) {
                            if(arr[x1][y1] != '.') {
                                array = array.plus(arr[x1][y1])
                                break
                            }
                            x1 += i
                            y1 += j
                        }
                    }
                }
            }
        return array
    }
}

fun main() {
    Day11().solve()
}
