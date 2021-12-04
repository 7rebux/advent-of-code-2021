package de.nosswald.aoc.days

import de.nosswald.aoc.Day

private const val ENTRIES = 25
private const val SIZE = 5

class Day04: Day(4, "Giant Squid") {

    data class Entry(val number: Int, var marked: Boolean)

    private fun isBingo(cols: List<Entry>, rows: List<Entry>): Boolean {
        return cols.all { it.marked } || rows.all { it.marked }
    }

    private fun getResults(): List<Int> {
        val bingoFields = mutableListOf<Int>()
        val results = mutableListOf<Int>()
        val markNumbers = inputList
            .first()
            .split(",")
            .map { it.trim().toInt() }
        val entries = inputList.subList(2, inputList.size).flatMap { line ->
            if (line.isNotEmpty()) {
                line.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { Entry(it.trim().toInt(), false) }
            } else
                emptyList()
        }

        markNumbers.forEach { markNumber ->
            entries.forEachIndexed { i, entry ->
                if (entry.number == markNumber) {
                    entry.marked = true

                    val index = i / ENTRIES
                    val start = index * ENTRIES
                    val (col, row) = listOf((i - start) % SIZE, (i - start) / SIZE)

                    val cols = (0 until SIZE).map { entries[start + it * SIZE + col] }
                    val rows = entries.subList(start + row * SIZE, start + row * SIZE + SIZE)

                    if (isBingo(cols, rows)) {
                        val field = entries.subList(start, start + ENTRIES)
                        val unmarkedEntries = field.filter { !it.marked }.sumOf { it.number }

                        if (!bingoFields.contains(index)) {
                            bingoFields += index
                            results += unmarkedEntries * markNumber
                        }
                    }
                }
            }
        }

        return results
    }

    override fun partOne(): Any {
        return getResults().first()
    }

    override fun partTwo(): Any {
        return getResults().last()
    }
}