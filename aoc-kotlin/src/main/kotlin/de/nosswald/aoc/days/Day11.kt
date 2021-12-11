package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day11: Day(11, "Dumbo Octopus") {

    private var grid = inputList.map { row -> row.toCharArray().map { it.digitToInt() }.toMutableList() }
    private var count = 0

    private fun getNeighbors(pos: Pair<Int, Int>): List<Pair<Int, Int>> {
        val neighbors: MutableList<Pair<Int, Int>> = mutableListOf()

        for (y in pos.second - 1..pos.second + 1) {
            for (x in pos.first - 1..pos.first + 1) {
                if ((x == pos.first && y == pos.second) ||
                        !(y in grid.indices && x in grid[y].indices))
                    continue

                neighbors.add(Pair(x, y))
            }
        }

        return neighbors
    }

    private fun simulate(): Boolean {
        grid.mapIndexed { y, rows -> List(rows.size) { x -> x to y }.forEach { pos ->
            grid[pos.second][pos.first]++
        } }

        grid.mapIndexed { y, rows -> List(rows.size) { x -> x to y }.forEach { pos ->
            if (grid[pos.second][pos.first] == 10) flash(pos)
        } }

        grid.mapIndexed { y, rows -> List(rows.size) { x -> x to y }.forEach { pos ->
            if (grid[pos.second][pos.first] == -1) grid[pos.second][pos.first] = 0
        } }

        return !grid.flatten().all { it == 0 }
    }

    private fun flash(pos: Pair<Int, Int>) {
        count++
        grid[pos.second][pos.first] = -1
        getNeighbors(pos).forEach {
            if (grid[it.second][it.first] != -1) {
                grid[it.second][it.first]++

                if (grid[it.second][it.first] >= 10)
                    flash(it)
            }
        }
    }

    override fun partOne(): Any {
        repeat(100) {
            simulate()
            grid.forEach { row -> println(row) }
            println()
        }

        return count
    }

    override fun partTwo(): Any {
        var step = 1

        grid = inputList.map { row -> row.toCharArray().map { it.digitToInt() }.toMutableList() }

        while (simulate())
            step++

        return step
    }
}