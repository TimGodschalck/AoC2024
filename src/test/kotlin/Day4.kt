import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

private fun task1(input: List<String>): Int {
    return countXmasInGrid(input)
}

private fun task2(input: List<String>): Int {
    return countX_masInGrid(input)
}

private fun countXmasInGrid(grid: List<String>): Int {
    val word = "XMAS"
    val directions = listOf(
        Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
        Pair(0, -1), Pair(0, 1),
        Pair(1, -1), Pair(1, 0), Pair(1, 1)
    )

    return grid.indices.sumOf { i ->
        grid[i].indices.sumOf { j ->
            directions.count { dir ->
                checkDirection(i, j, grid, dir, word)
            }
        }
    }
}

private fun checkDirection(i: Int, j: Int, grid: List<String>, dir: Pair<Int, Int>, word: String): Boolean {
    var x = i
    var y = j

    for (ch in word) {
        if (x < 0 || y < 0 || x >= grid.size || y >= grid[0].length) {
            return false
        }
        if (grid[x][y] != ch) {
            return false
        }

        x += dir.first
        y += dir.second
    }

    return true
}


private fun countX_masInGrid(grid: List<String>): Int {
    var count = 0
    val n = grid.size
    val m = grid[0].length

    for(i in 1 until n - 1) {
        for(j in 1 until m - 1) {
            if(grid[i][j] == 'A') {
                val leftTop = grid[i-1][j-1]
                val rightBottom = grid[i+1][j+1]
                val rightTop = grid[i-1][j+1]
                val leftBottom = grid[i+1][j-1]

                if (checkX_MAS(leftTop, rightBottom, rightTop, leftBottom))
                    count++
            }
        }
    }
    return count
}

private fun checkX_MAS(leftTop: Char, rightBottom: Char, rightTop: Char, leftBottom: Char): Boolean {
    val pairs = listOf(Pair(leftTop, rightBottom), Pair(rightTop, leftBottom))
    return pairs.all {
        (it.first == 'M' && it.second == 'S') || (it.first == 'S' && it.second == 'M')
    }
}

class Day4 {

    @Test
    fun testTask1TestInput(){
        val expected = 2
        val input = readInput("4test")
        val answer = task1(input)
        assertEquals(expected, answer)
    }

    @Test
    fun testTask1Input() {
        val expected = 2557
        val input = readInput("4")
        val answer = task1(input)
        assertEquals(
            expected,
            answer
        )
    }

    @Test
    fun testTask2TestInput(){
        val expected = 3
        val input = readInput("4test")
        val answer = task2(input)
        assertEquals(expected, answer)
    }

    @Test
    fun testTask2Input() {
        val expected = 1854
        val input = readInput("4")
        val answer = task2(input)
        assertEquals(
            expected,
            answer
        )
    }
}