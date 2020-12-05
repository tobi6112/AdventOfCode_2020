package aoc.utils.codegen

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import org.jsoup.Jsoup
import java.io.File
import javax.xml.parsers.DocumentBuilder

fun main(args: Array<String>) {
    val parser = ArgParser("aoc")
    val day by parser.option(ArgType.Int, shortName = "d", description = "Day to generate").required()
    parser.parse(args)

    val cookie: String = System.getenv("AOC_SESSION")?: throw Exception("No session cookie found. Set AOC_SESSION enviroment variable")

    createReadme(day, cookie)
}

fun createReadme(day: Int, sessionCookie: String) {
    val dir = File("src/main/kotlin/aoc/exercises/day$day/")
    dir.mkdirs()
    val file = File(dir, "README.md")
    if(!file.exists()) {
        file.createNewFile()
    }
    val exercise = parseDocument(getExercise(day, sessionCookie))
    file.writeText(exercise)
}

fun parseDocument(html: String) : String {
    return Jsoup.parse(html).getElementsByClass("day-desc").joinToString("\n") { it.html() }
}

fun getExercise(day: Int, sessionCookie: String) : String {
    val (_, resonse, result) = "https://adventofcode.com/2020/day/$day"
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