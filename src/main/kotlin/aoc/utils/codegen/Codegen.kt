package aoc.utils.codegen

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import java.io.File

fun main(args: Array<String>) {

    println(System.getProperty("d"))

    val parser = ArgParser("aoc")
    val day by parser.option(ArgType.Int, shortName = "d", description = "Day to generate").required()
    val skip by parser.option(ArgType.Boolean, shortName = "s", description = "Skips the input generation").default(false)
    parser.parse(args)

    if(!skip) {
        val cookie: String = System.getenv("AOC_SESSION")
            ?: throw Exception("No session cookie found. Set AOC_SESSION enviroment variable or use -s")
        gen(day, cookie)
    } else {
        gen(day, "", true)
    }


}

fun gen(day: Int, sessionCookie: String, skip: Boolean = false) {
    createTestFile(day)
    createTest(day)
    createExercise(day)
    if(!skip) {
        createInput(day, sessionCookie)
    }
}

fun createTestFile(day: Int) {
    val dir = File("src/test/resources/inputs")
    dir.mkdirs()
    val file = File(dir, "Day${day}")
    if(!file.exists()) {
        file.createNewFile()
    }
}

fun createTest(day: Int) {
    val dir = File("src/test/kotlin/aoc/exercises")
    dir.mkdirs()
    val file = File(dir, "Day${day}Test.kt")
    if(!file.exists()) {
        file.createNewFile()
    }
    file.writeText(getTest(day))
}

fun getTest(day: Int) : String {
    return object {}.javaClass.getResource("/codegen/ExerciseTest").readText().replace("{{day}}", day.toString())
}

fun createExercise(day: Int) {
    val dir = File("src/main/kotlin/aoc/exercises")
    dir.mkdirs()
    val file = File(dir, "Day${day}.kt")
    if(!file.exists()) {
        file.createNewFile()
    }
    file.writeText(getExercise(day))
}

fun getExercise(day: Int) : String {
    return object {}.javaClass.getResource("/codegen/Exercise").readText().replace("{{day}}", day.toString())
}

fun createInput(day: Int, sessionCookie: String) {
    val dir = File("src/main/resources/inputs/")
    dir.mkdirs()
    val file = File(dir, "Day$day")
    if(!file.exists()) {
        file.createNewFile()
    }
    file.writeText(getInput(day, sessionCookie))
}

fun getInput(day: Int, sessionCookie: String) : String {
    val (_, resonse, result) = "https://adventofcode.com/2020/day/$day/input"
        .httpGet()
        .appendHeader("cookie", "session=$sessionCookie")
        .responseString()

    when(result) {
        is Result.Success -> {
            return result.get()
        }
        is Result.Failure -> {
            throw result.error.exception
        }
    }
}