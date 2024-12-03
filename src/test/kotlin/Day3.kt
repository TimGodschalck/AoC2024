import org.junit.jupiter.api.Test
import util.readInput
import kotlin.test.assertEquals

private fun task1(input: List<String>): Int {
    val fullMemory = input.joinToString("")

    return "mul\\((\\d+),(\\d+)\\)".toRegex().findAll(fullMemory).sumOf { match ->
        val (num1, num2) = match.destructured
        num1.toInt() * num2.toInt()
    }
}

private fun task2(input: List<String>): Int {
    val fullMemory = input.joinToString("")
    var isDo = true
    var sum = 0

    """(don't\(\)|do\(\)|mul\((\d+),(\d+)\))""".toRegex().findAll(fullMemory).forEach { match ->
        when {
            match.value.startsWith("do(")   -> isDo = true
            match.value.startsWith("don't(") -> isDo = false
            match.value.startsWith("mul(") -> {
                if (isDo) {
                    val num1 = match.groupValues[2].toInt()
                    val num2 = match.groupValues[3].toInt()
                    sum += num1 * num2
                }
            }
        }
    }
    return sum
}

class Day3 {
    @Test
    fun testTask1Input() {
        val expected = 179571322
        val input = readInput("3")
        val answer = task1(input)
        assertEquals(
            expected,
            answer
        )
    }

    @Test
    fun testTask2Input() {
        val expected = 103811193
        val input = readInput("3")
        val answer = task2(input)
        assertEquals(
            expected,
            answer
        )
    }
}