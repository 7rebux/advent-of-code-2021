package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day09: Day(9, "Smoke Basin") {

    private var seen: MutableList<Pair<Int, Int>> = mutableListOf()

    @OptIn(ExperimentalStdlibApi::class)
    private fun getNeighbors(field: Array<Array<Int>>, x: Int, y: Int): List<Pair<Int, Int>> {
        val offsets = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

        return buildList {
            offsets.forEach { off -> try { field[y + off.second][x + off.first]; add(Pair(x + off.first, y + off.second)) } catch (_: ArrayIndexOutOfBoundsException) { } }
        }
    }

    private fun getRiskLevel(field: Array<Array<Int>>, x: Int, y: Int): Int =
        if (getNeighbors(field, x, y).all { field[it.second][it.first] > field[y][x] }) (field[y][x] + 1) else 0

    private fun getBasinSize(field: Array<Array<Int>>, x: Int, y: Int, prev: Int = 0): Int {
        var basinSize = prev

        getNeighbors(field, x, y).forEach {
            if (field[it.second][it.first] != 9 && it !in seen) {
                seen += it
                basinSize = getBasinSize(field, it.first, it.second, ++basinSize)
            }
        }

        return basinSize
    }

    override fun partOne(): Any {
        val field = Array(inputList.size) { Array(inputList[0].length) { 0 } }
        inputList.forEachIndexed { index, s -> field[index] = s.toCharArray().map { it.digitToInt() }.toTypedArray() }

        return field.mapIndexed { y, rows -> rows.mapIndexed { x, _ -> x to y }.sumOf { getRiskLevel(field, it.first, it.second) } }.sum()
    }

    override fun partTwo(): Any {
        val field = Array(inputList.size) { Array(inputList[0].length) { 0 } }
        val basinSizes = mutableListOf<Int>()

        inputList.forEachIndexed { index, s -> field[index] = s.toCharArray().map { it.digitToInt() }.toTypedArray() }

        val lowPoints = field.mapIndexed { y, rows -> rows.mapIndexed { x, _ -> x to y }.filter { getRiskLevel(field, it.first, it.second) > 0 } }.flatten()
        lowPoints.forEach { basinSizes.add(getBasinSize(field, it.first, it.second)) }

        return basinSizes.sortedDescending().take(3).reduce(Int::times) // multiplies every element
    }
}