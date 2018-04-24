package algorithm

import java.util.*

class KeyGenerator(private val input: ByteArray) {
    val PC1 = byteArrayOf(
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    )
    val PC2 = byteArrayOf(
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    )
    private val rotations = byteArrayOf(1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1)
    lateinit var key: ByteArray
    lateinit var c: ByteArray
    lateinit var d: ByteArray

    fun keyGenerate(): KeyGenerator {
        val newBits = ByteArray(PC1.size)
        for (i in 0 until newBits.size)
            newBits[i] = input[PC1[i] - 1]
        key = newBits
        return this
    }

    fun createLeftAndRightSideOfKey(): KeyGenerator {
        return this.createLeftSideOfKey().createRightSideOfKey()
    }

    fun createLeftSideOfKey(): KeyGenerator {
        c = key.copyOf(key.size / 2)
        return this
    }

    fun createRightSideOfKey(): KeyGenerator {
        d = key.copyOfRange(key.size / 2, key.size)
        return this
    }

    fun leftShift(bits: ByteArray, n: Byte): ByteArray {
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

    //Kn
    fun generateRoundKeys(round: Int): ByteArray {
        val C1: ByteArray
        val D1: ByteArray
        val rotationTimes = rotations[round]
        C1 = leftShift(c, rotationTimes)
        D1 = leftShift(d, rotationTimes)
        val CnDn = C1 + D1
        //return permuted choice 2
        return algorithm.permuteArrayUsingPatternArray(CnDn, PC2)
    }

}
