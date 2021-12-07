package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day07: Day(7, "The Treachery of Whales") {

    private val maxHorizontalPos = inputString.split(",").maxOf { it.toInt() }

    private fun getMinimumFuel(increment: Boolean): Int {
        val fuel = Array(maxHorizontalPos) { 0 }

        for (x2 in 1..maxHorizontalPos) {
            inputString.split(",").map { it.toInt() }.forEach { x1 ->
                var x = x1
                var incrementAmount = 1

                while (x != x2) {
                    x += if (x1 - x2 > 0) -1 else 1
                    fuel[x2-1] += incrementAmount
                    if (increment) incrementAmount++
                }
            }
        }

        return fuel.minOrNull()!!
    }

    override fun partOne(): Any {
        return getMinimumFuel(increment = false)
    }

    override fun partTwo(): Any {
        return getMinimumFuel(increment = true)
    }
}