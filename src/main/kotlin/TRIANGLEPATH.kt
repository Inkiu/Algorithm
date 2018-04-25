import java.util.*

class TRIANGLEPATH {
    private val scanner = Scanner(System.`in`)

    fun main(args: Array<String>) {
        val caseCount = scanner.nextInt()
        for (c in 0 until caseCount) {
            solution()
        }
    }

    private fun solution() {
        val lineCount = scanner.nextInt()
        val sourceList = scanNumbers(lineCount)

        for (step in 1 until sourceList.size) {
            val prevSource = sourceList[step - 1]
            val source = sourceList[step] as MutableList

            for (i in 0 until source.size) {
                val up = if (i == step) 0 else prevSource[i] + source[i]
                val left = if (i == 0) 0 else prevSource[i - 1] + source[i]
                source[i] = Math.max(up, left)
            }
        }

        println(sourceList[sourceList.size - 1].max())
    }

    private fun scanNumbers(lineCount: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for (i in 0 until lineCount) {
            val list = mutableListOf<Int>()
            for (n in 0..i) list.add(scanner.nextInt())
            result.add(list)
        }
        return result
    }
}