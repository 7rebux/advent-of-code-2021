package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day14: Day(14, "Extended Polymerization") {

    private fun solve(times: Int): Long {
        val map = inputList.filter { it.contains(" -> ") }.map { it.split(" -> ") }.associate { it[0] to it[1] }
        var pairs = mutableMapOf<String, Long>()
        val chars = mutableMapOf<Char, Long>()

        inputList.first().windowed(2).forEach { pairs.increment(it) }

        repeat(times) {
            val temp = mutableMapOf<String, Long>()

            pairs.forEach { (k, v) ->
                temp.increment(k[0] + map[k]!!, v)
                temp.increment(map[k]!! + k[1], v)
            }

            pairs = temp
        }

        pairs.forEach { (k, v) -> chars.increment(k[0], v) }
        chars.increment(inputList.first().last())

        return chars.values.sorted().let { it.last() - it.first() }
    }

    private fun <K> MutableMap<K, Long>.increment(key: K, by: Long = 1) {
        put(key, getOrDefault(key, 0) + by)
    }

    override fun partOne(): Any {
        return solve(10)
    }

    override fun partTwo(): Any {
        return solve(40)
    }
}