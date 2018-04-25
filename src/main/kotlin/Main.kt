val map = mutableMapOf<Int, Long>(
        0 to 0,
        1 to 1
)
val sourceString =
"""1
2 4
8 16 8
32 64 32 64
128 256 128 256 128"""

//val sourceList = listOf<List<Int>>(
//        mutableListOf(6),
//        mutableListOf(1, 2),
//        mutableListOf(3, 7, 4),
//        mutableListOf(9, 4, 1, 7),
//        mutableListOf(2, 7, 5, 9, 4)
//
//)

fun main(args: Array<String>) {
    val sourceList = scanNumbers()


    for (step in 1 until sourceList.size) {
        val prevSource = sourceList[step - 1]
        val source = sourceList[step] as MutableList

        for (i in 0 until source.size) {
            val up = if (i == step) 0 else prevSource[i] + source[i]
            val left = if (i == 0) 0 else prevSource[i - 1]  + source[i]
            source[i] = Math.max(up, left)
        }
    }
    println(sourceList[sourceList.size - 1].max())
}

fun scanNumbers(): List<List<Int>> {
    val listOfList = mutableListOf<List<Int>>()
    sourceString.split("\n")
            .forEach {
                listOfList.add(it.split(" ").map { it.toInt() }.toMutableList())
            }
    return listOfList
}

fun dynamicFibonacci(n: Int): Long {
    if (n !in map.keys)
        map[n] = dynamicFibonacci(n - 1) + dynamicFibonacci(n - 2)
    return map[n]!!
}

fun pureFibonacci(n: Int): Long {
    if (n == 0) return 0
    if (n == 1) return 1
    return pureFibonacci(n - 1) + pureFibonacci(n - 2)
}