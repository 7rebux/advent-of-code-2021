package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import kotlin.math.abs
import kotlin.math.max

class Day05: Day(5, "Hydrothermal Venture") {

    private fun countOverlaps(diagonals: Boolean): Int {
        val diagram = Array(1000) { Array(1000) { 0 } }

        inputList.forEach { line ->
            val pairs = line.split(" -> ")
            val (x1, y1) = pairs[0].split(",").map { it.toInt() }
            val (x2, y2) = pairs[1].split(",").map { it.toInt() }

            val dx = x2 - x1
            val dy = y2 - y1

            (0..max(abs(dx), abs(dy))).forEach { i ->
                val x = x1 + (if (dx > 0) 1 else (if (dx < 0) -1 else 0)) * i
                val y = y1 + (if (dy > 0) 1 else (if (dy < 0) -1 else 0)) * i

                if (diagonals)
                    diagram[y][x] = diagram[y][x] + 1
                else if (dx == 0 || dy == 0)
                    diagram[y][x] = diagram[y][x] + 1
            }
        }

        return diagram.flatten().count { it >= 2 }
    }

    override fun partOne(): Any {
        return countOverlaps(diagonals = false)
    }

    override fun partTwo(): Any {
        return countOverlaps(diagonals = true)
    }
}