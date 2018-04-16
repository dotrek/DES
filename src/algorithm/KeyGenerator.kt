package algorithm

import java.util.*

/**
 * Created by dotre on 15.04.2018.
 */
class KeyGenerator(private val input: IntArray) {
    val PC1 = intArrayOf(
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    )
    private val rotations = intArrayOf(1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1)
    private lateinit var key: IntArray
    lateinit var c: IntArray
    lateinit var d: IntArray

    fun keyGenerate(): IntArray {
        val newBits = IntArray(PC1.size)
        for (i in 0 until newBits.size)
            newBits[i] = input[PC1[i] - 1]
        key = newBits
        return key
    }

    fun getLeftSideOfKey(): IntArray {
        c = key.copyOf(key.size / 2)
        return c
    }

    fun getRightSideOfKey(): IntArray {
        d = key.copyOfRange(key.size / 2, key.size)
        return d
    }

    fun leftShift(bits: IntArray, n: Int): IntArray {
        val answer = Arrays.copyOf(bits, bits.size)
        for (i in 0 until n) {
            val temp = answer[0]
            for (j in 0 until answer.size - 1) {
                answer[j] = answer[j + 1]
            }
            answer[bits.size - 1] = temp
        }
        return answer
    }
}
