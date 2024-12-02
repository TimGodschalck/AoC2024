import org.junit.jupiter.api.Test
import util.readInput
import kotlin.math.absoluteValue
import kotlin.test.assertEquals

private fun task1(input: List<String>): Int {
    val (leftList, rightList) = extractValues(input)
    var totalDistance = 0
    for (i in leftList.indices) {
        totalDistance += (leftList[i] - rightList[i]).absoluteValue
    }

    return totalDistance
}

private fun task2(input: List<String>): Int {
    val (leftList, rightList) = extractValues(input)
    var similarityScore = 0
    for (number in leftList) {
        val frequency = rightList.count { it == number }
        similarityScore += number * frequency
    }

    return similarityScore
}

fun extractValues(input: List<String>): Pair<List<Int>, List<Int>> {
    val pairs = input.map { it -> it.split("\\s+".toRegex()).map { it.toInt() } }
    val leftList = pairs.map { it[0] }.sorted()
    val rightList = pairs.map { it[1] }.sorted()
    return leftList to rightList
}

class Day1 {
    @Test
    fun testTask1Input() {
        val expected = 1506483
        val input = readInput("1")
        val answer = task1(input)

        assertEquals(expected, answer)
    }

    @Test
    fun testTask2Input() {
        val expected = 23126924
        val input = readInput("1")
        val answer = task2(input)

        assertEquals(expected, answer)
    }
}