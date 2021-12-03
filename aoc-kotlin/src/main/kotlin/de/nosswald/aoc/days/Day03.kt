package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day03: Day(3, "Binary Diagnostic") {

    private fun getMostCommonBit(list: List<String>, index: Int): Char {
        var count = 0
        list.forEach { count += if (it.toCharArray()[index] == '1') 1 else -1 }
        return if (count >= 0) '1' else '0'
    }

    override fun partOne(): Any {
        var (gamma, epsilon) = listOf("", "")

        inputList.first().toCharArray().forEachIndexed { i, _ ->
            gamma += if (getMostCommonBit(inputList, i) == '1') '0' else '1'
            epsilon += getMostCommonBit(inputList, i)
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    override fun partTwo(): Any {
        var inputListClone = inputList.toMutableList()

        run oxygen@ {
            inputList.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputListClone, i)

                inputListClone.toMutableList().forEach {
                    if (it[i] != mostCommon)
                        inputListClone.remove(it)

                    if (inputListClone.size == 1)
                        return@oxygen
                }
            }
        }
        val oxygen = inputListClone.first()

        inputListClone = inputList.toMutableList()

        run scrubber@ {
            inputList.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputListClone, i)

                inputListClone.toMutableList().forEach {
                    if (it[i] != if (mostCommon == '1') '0' else '1')
                        inputListClone.remove(it)

                    if (inputListClone.size == 1)
                        return@scrubber
                }
            }
        }
        val scrubber = inputListClone.first()

        return Integer.parseInt(oxygen, 2) * Integer.parseInt(scrubber, 2)
    }
}