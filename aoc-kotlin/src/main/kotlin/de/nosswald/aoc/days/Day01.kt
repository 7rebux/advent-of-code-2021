package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day01: Day(1, "Sonar Sweep") {

    override fun partOne(): Any {
        var previous = Int.MAX_VALUE
        var increments = 0

        inputList.map { it.toInt() }.forEach { if (it > previous) increments++; previous = it }

        return increments
    }

    override fun partTwo(): Any {
        var previous = Int.MAX_VALUE
        var increments = 0

        for ((i, x) in inputList.withIndex()) {
            var sum = inputList.subList(i, i + 3).sumOf { it.toInt() }

            if (sum > previous)
                increments++

            previous = sum

            if (i+3 > inputList.size-1)
                break
        }

        return increments
    }
}