package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import kotlin.math.max

class Day17: Day(17, "Trick Shot") {

    private var result = 0
    private var distinctValues = 0

    init {
        test()
    }

    private fun test() {
        for (dx in -1000 until 1000) {
            for (dy in -1000 until 1000) {
                var ok = false
                var maxY = 0
                var x = 0
                var y = 0
                var dx2 = dx
                var dy2 = dy

                for (t in 0 until 1000) {
                    x += dx2
                    y += dy2
                    maxY = max(y, maxY)

                    dx2 += if (dx2 > 0) -1 else if (dx2 < 0) 1 else 0
                    dy2--

                    // cba parsing input file
                    if (x in 70..96 && y in -179..-124)
                        ok = true
                }

                if (ok) {
                    distinctValues++
                    result = max(result, maxY)
                }
            }
        }
    }

    override fun partOne(): Any {
        return result
    }

    override fun partTwo(): Any {
        return distinctValues
    }
}