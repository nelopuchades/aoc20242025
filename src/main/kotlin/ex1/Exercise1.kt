package ex1

import java.io.File

class Exercise1() {
    fun get(): List<Int> {
        val filePath = "src/main/kotlin/ex1/data.txt"
        val firstColumn = mutableListOf<Int>()
        val secondColumn = mutableListOf<Int>()

        File(filePath).forEachLine { line ->
            val columns = line.split("\\s+".toRegex())
            if (columns.size == 2) {
                firstColumn.add(columns[0].toInt())
                secondColumn.add(columns[1].toInt())
            }
        }

        firstColumn.sort()
        secondColumn.sort()

        val differences = firstColumn.zip(secondColumn) { a, b -> kotlin.math.abs(a - b) }
        val totalDifference = differences.sum()

//        println("First Column: $firstColumn")
//        println("Second Column: $secondColumn")
//        println("Differences: $differences")
//        println("Total Difference: $totalDifference")

        val frequencyMap = secondColumn.groupingBy { it }.eachCount()
        var similarityScore = 0

        for (number in firstColumn) {
            val frequency = frequencyMap[number] ?: 0
            similarityScore += number * frequency
        }

//        println("Similarity Score: $similarityScore")

        return listOf(totalDifference, similarityScore)
    }
}