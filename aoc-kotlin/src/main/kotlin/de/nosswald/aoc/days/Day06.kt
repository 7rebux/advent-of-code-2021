package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day06: Day(6, "Lanternfish") {

    private fun simulate(days: Int): Long {
        var fishTypes = Array<Long>(9) { 0 } // 9 "types" of fishes

        for (i in 0..8) {
            fishTypes[i] = inputString.split(",").count { it.toLong() == i.toLong() }.toLong()
        }

        for (i in 0 until days) {
            val newFishTypes = Array<Long>(9) { 0 }

            for (j in 0..8) {
                if (j == 0) {
                    newFishTypes[6] += fishTypes[j]
                    newFishTypes[8] += fishTypes[j]
                } else
                    newFishTypes[j-1] += fishTypes[j]
            }

            fishTypes = newFishTypes
        }

        return fishTypes.sumOf { it }
    }

    override fun partOne(): Any {
        return simulate(80)
    }

    override fun partTwo(): Any {
        return simulate(256)
    }
}