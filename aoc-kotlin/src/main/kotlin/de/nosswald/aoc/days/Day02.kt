package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day02 : Day(2, "Dive!") {

    override fun partOne(): Any {
        var (depth, horizontal) = listOf(0, 0)

        inputList.forEach {
            val (instruction, value) = it.split(" ")

            when (instruction) {
                "forward" -> horizontal += value.toInt()
                "down" -> depth += value.toInt()
                "up" -> depth -= value.toInt()
            }
        }

        return depth * horizontal
    }

    override fun partTwo(): Any {
        var (depth, horizontal, aim) = listOf(0, 0, 0)

        inputList.forEach {
            val (instruction, value) = it.split(" ")

            when (instruction) {
                "forward" -> {
                    horizontal += value.toInt()
                    depth += aim * value.toInt()
                }
                "down" -> aim += value.toInt()
                "up" -> aim -= value.toInt()
            }
        }

        return depth * horizontal
    }
}