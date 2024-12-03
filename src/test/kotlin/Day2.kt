import org.junit.jupiter.api.Test
import util.readInput
import kotlin.test.assertEquals

private fun task1(input: List<String>): Int {
    val reports = input.map { it -> it.split("\\s+".toRegex()).map { it.toInt() } }
    var safeReports = 0

    reports.forEach { report ->
        if (isSafe(report)) {
            safeReports++
        }
    }

    return safeReports
}

private fun task2(input: List<String>): Int {
    val reports = input.map { it -> it.split("\\s+".toRegex()).map { it.toInt() } }
    var safeReports = 0

    reports.forEach { report ->
        if (isSafe(report)) {
            safeReports++
        } else {
            report.indices.any { index ->
                val newReport = report.toMutableList().apply { removeAt(index) }
                if (isSafe(newReport)) {
                    safeReports++
                    return@any true
                }
                false
            }
        }
    }

    return safeReports
}

private fun isSafe(report: List<Int>): Boolean {
    val differences = report.windowed(2).map { it.last() - it.first() }

    val isAllIncreasing = differences.all { it in 1..3 }
    val isAllDecreasing = differences.all { it in -3..-1 }

    return isAllIncreasing || isAllDecreasing
}


class Day2 {
    @Test
    fun testTask1Input() {
        val expected = 202
        val input = readInput("2")
        val answer = task1(input)
        assertEquals(
            expected,
            answer
        )
    }

    @Test
    fun testTask2Input() {
        val expected = 271
        val input = readInput("2")
        val answer = task2(input)
        assertEquals(
            expected,
            answer
        )
    }
}