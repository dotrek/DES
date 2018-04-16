package algorithm

import org.testng.annotations.Test
import org.testng.internal.junit.ArrayAsserts

class KeyGeneratorTest {
    val table = intArrayOf(
            1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24,
            25, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48,
            49, 50, 51, 52, 53, 54, 55, 56,
            57, 58, 59, 60, 61, 62, 63, 64
    )


    val keyGenerator = KeyGenerator(table)

    @Test
    fun checkIfKeyGeneratesProperly() {
        val key = keyGenerator.keyGenerate()
        ArrayAsserts.assertArrayEquals(keyGenerator.PC1, key.key)
    }

    @Test
    fun checkIfLeftSideIsFirstHalfOfKey() {
        val leftSide = intArrayOf(
                57, 49, 41, 33, 25, 17, 9,
                1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36
        )

        keyGenerator.keyGenerate()
        val c = keyGenerator.createLeftSideOfKey()
        ArrayAsserts.assertArrayEquals(leftSide, c.c)
    }

    @Test
    fun checkIfRightSideIsSecondHalfOfKey() {
        val rightSide = intArrayOf(
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14, 6, 61, 53, 45, 37, 29,
                21, 13, 5, 28, 20, 12, 4
        )
        val key = keyGenerator.keyGenerate()
        val d = key.createRightSideOfKey()
        ArrayAsserts.assertArrayEquals(rightSide, d.d)
    }

    @Test
    fun checkLeftShiftFunctionality() {
        val caseArray = intArrayOf(1, 2, 3, 4, 5)
        val caseArrayShifted = intArrayOf(2, 3, 4, 5, 1)
        val leftShift = keyGenerator.leftShift(caseArray, 1)
        ArrayAsserts.assertArrayEquals(caseArrayShifted, leftShift)
    }
}