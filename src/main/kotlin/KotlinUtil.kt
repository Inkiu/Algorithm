import java.util.*

object KotlinUtil {
    fun makeRandomArray(arrSize: Int = 100,
                        elementBounce: Int = 1000,
                        distinct: Boolean = false,
                        includeMinus: Boolean = false): IntArray {
        val random = Random()
        val list = mutableListOf<Int>()

        for (i in 0 until arrSize) {
            var candidate = random.nextInt(elementBounce + 1)
            if (includeMinus) candidate -= elementBounce / 2
            while (distinct && list.contains(candidate)) {
                candidate = random.nextInt(elementBounce + 1)
                if (includeMinus) candidate -= elementBounce / 2
            }

            list.add(candidate)

        }
        return list.toIntArray()
    }
}