package aoc

import aoc.base.AbstractExercise
import aoc.utils.Color
import aoc.utils.ColoredPrinter
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import mu.KotlinLogging
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.createInstance

fun main(args: Array<String>) {

    val logger = KotlinLogging.logger { }

    val parser = ArgParser("aoc")
    val days by parser.option(ArgType.String, shortName = "d", description = "Days to execute as List e.g. 1,2,3,4").default((1..25).joinToString(","))

    parser.parse(args)

    val d = days.split(",").mapNotNull { it.toIntOrNull() }.filter { it in 1..25 }.distinct().sorted()

    val abstractExerciseClass = Class.forName("aoc.base.AbstractExercise").kotlin

    d.forEach { i ->
        try {
            val kClass = Class.forName("aoc.exercises.Day$i").kotlin
            if (kClass.allSuperclasses.contains(abstractExerciseClass)) {
                try {
                    val day: AbstractExercise = kClass.createInstance() as AbstractExercise
                    day.solve()
                } catch (e2: Exception) {
                    logger.error(e2) {
                        "Couldn't instantiate ${kClass.qualifiedName}"
                    }
                }
            } else {
                logger.error("${kClass.qualifiedName} does not have superclass ${abstractExerciseClass.qualifiedName}.")
            }
        } catch (e: ClassNotFoundException) {
            ColoredPrinter.println("========= Day $i =========", Color.GREEN)
            ColoredPrinter.println("not implemented yet", Color.RED)
            ColoredPrinter.print(System.lineSeparator())
        }

    }
}