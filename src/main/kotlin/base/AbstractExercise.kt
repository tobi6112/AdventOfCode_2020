package base

abstract class AbstractExercise {
    fun readFile(filename: String): List<String> {
        return this::class.java.getResource(filename).readText().split(System.lineSeparator())
    }

    abstract fun solve()
}
