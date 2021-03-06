package algorithm

import kotlin.experimental.and
import kotlin.experimental.xor


class Algorithm {
    private var iteration = 0;
    val initialPermutationTable = byteArrayOf(
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    )
    val E = byteArrayOf(
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    )
    val S = arrayOf(
            byteArrayOf(
                    14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
                    0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
                    4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
                    15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
            ),
            byteArrayOf(
                    15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
                    3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
                    0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
                    13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
            ),
            byteArrayOf(
                    10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
                    13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
                    13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
                    1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
            ),
            byteArrayOf(
                    7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
                    13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
                    10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
                    3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
            ),
            byteArrayOf(
                    2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
                    14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
                    4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
                    11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
            ),
            byteArrayOf(
                    12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
                    10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
                    9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
                    4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
            ),
            byteArrayOf(
                    4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
                    13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
                    1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
                    6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
            ),
            byteArrayOf(
                    13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
                    1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
                    7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
                    2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
            )
    )


    fun cipher(input: String) {
        //change string to inputBinaryByteArray
        val inputByteArray = input.toByteArray(Charsets.UTF_8)
        println("After utf_8 encoding: ${inputByteArray.contentToString()}")
        val inputBinaryString = changeByteArrayToBinaryString(inputByteArray)
        val inputBinaryCharArray = inputBinaryString.toCharArray().toTypedArray()
        println("After binary converting: ${inputBinaryCharArray.contentToString()} Length: ${inputBinaryCharArray.size}")
        val inputBinaryByteArray = convertCharArrayToByteArray(inputBinaryCharArray)
        println("converted to byte array: ${inputBinaryByteArray.contentToString()} , $inputBinaryByteArray")
        //initial permutation
        val permutedInput = permuteArrayUsingPatternArray(inputBinaryByteArray, initialPermutationTable)
        println("After initial permutation: ${changeByteArrayToBinaryString(permutedInput)}")
        //split into two sides
        val L = getLeftSide(permutedInput)
        println("L[$iteration]: ${L.contentToString()}")
        val R = getRightSide(permutedInput)
        println("R[$iteration]: ${R.contentToString()}")
        //generate key
        val keyGenerator = KeyGenerator(inputBinaryByteArray)
        val key = keyGenerator.keyGenerate().createLeftAndRightSideOfKey()
        println("Key: ${key.key.contentToString()}")
        //Permute R with extension array
        val extendedR = permuteArrayUsingPatternArray(R, E)

        val sTable = extendedR xor keyGenerator.generateRoundKeys(iteration++)

        var splitTable = splitIntoEightParts(sTable)
    }

    private fun convertCharArrayToByteArray(charArray: Array<Char>): ByteArray {
        val intArray = charArray.map { if (it == '0') 0 else 1 }.toIntArray()
        return convertIntArrayToByteArray(intArray)
    }

    private fun convertIntArrayToByteArray(intArray: IntArray): ByteArray =
            intArray.foldIndexed(ByteArray(intArray.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }

    private fun changeByteArrayToBinaryString(array: ByteArray): String {
        val builder = StringBuilder()
        val stringArray = array.map { getByteBinaryStr(it) }
        stringArray.forEach { builder.append(it) }
        return builder.toString()
    }

    private fun getByteBinaryStr(byte: Byte): String {
        var result = ""
        var bits = byte
        var counter = java.lang.Byte.SIZE
        val mask = (0b10000000).toByte()
        while (counter > 0) {
            val c = if (bits.and(mask) == mask) '1' else '0'
            result += c

            bits = bits.toInt().shl(1).toByte()
            counter -= 1
        }
        return result
    }

    fun permuteArrayUsingPatternArray(input: ByteArray, patternArray: ByteArray): ByteArray {
        val newBits = ByteArray(patternArray.size)
        for (i in 0 until patternArray.size) {
            newBits[i] = input[patternArray[i] - 1]
        }
        return newBits
    }

    fun getLeftSide(input: ByteArray): ByteArray {
        return input.copyOf(input.size / 2)
    }

    fun getRightSide(input: ByteArray): ByteArray {
        return input.copyOfRange(input.size / 2, input.size)
    }

    fun splitIntoEightParts(input: ByteArray): Array<ByteArray> {
        val resultArraySize = input.size/8
        val splitArray: Array<ByteArray> = Array(8, { ByteArray(resultArraySize) })
        var j = 0
        for (i in 0 until splitArray.size) {
            if (j >= input.size) break
            splitArray[i] = input.copyOfRange(j, j + resultArraySize-1)
            j += resultArraySize
        }
        print("Split array: ")
        splitArray.forEach { print(it.contentToString()) }
        return splitArray
    }

}

private infix fun ByteArray.xor(secondArray: ByteArray): ByteArray {

    val resultArray = ByteArray(size)
    for (i in 0 until size) {
        resultArray[i] = this[i] xor secondArray[i]
    }
    return resultArray
}


