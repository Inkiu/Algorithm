import java.util.*

/**
 * Created by inkiu on 2018. 5. 19..
 */

class DIAMONDPATH {
    private val scanner = Scanner(System.`in`)

    fun main(args: Array<String>) {
        val caseCount = scanner.nextInt()
        for (c in 0 until caseCount) {
            solution()
        }
    }

    private fun solution() {
        val maxCol = scanner.nextInt()

        var maxList = listOf(PathInt(scanner.nextInt()))

        for (c in 2 .. maxCol) {
            maxList = eachLine(c, maxList)
            println(maxList)
        }
        for (c in maxCol - 1 downTo 1) {
            maxList = eachLine(c, maxList)
            println(maxList)
        }

        println("answer: ${maxList[0]}")
    }

    private fun eachLine(columns: Int, prev: List<PathInt>): List<PathInt> {
        val next = scanList(columns)

        val left = if (prev.size < next.size) -1 else 0
        val right = if (prev.size < next.size) 0 else 1

        for (i in 0 until next.size) {
            val leftOne = if (i + left >= 0) prev[i + left].value else Int.MIN_VALUE
            val rightOne = if (i + right >= prev.size) Int.MIN_VALUE else prev[i + right].value

            val newPath = mutableListOf<Int>()
            if (leftOne >= rightOne) {
                next[i] = PathInt(
                        prev[i + left].value + next[i].value,
                        newPath.apply { addAll(prev[i +left].path); add(1) }
                )
            } else {
                next[i] = PathInt(
                        prev[i + right].value + next[i].value,
                        newPath.apply { addAll(prev[i +right].path); add(0) }
                )
            }
        }

        return next
    }

    private fun scanList(columns: Int): MutableList<PathInt> {
        val list = mutableListOf<PathInt>()
        for (c in 0 until columns) {
            list.add(PathInt(scanner.nextInt()))
        }
        return list
    }

    data class PathInt(val value: Int,
                       val path: MutableList<Int> = mutableListOf<Int>())
}