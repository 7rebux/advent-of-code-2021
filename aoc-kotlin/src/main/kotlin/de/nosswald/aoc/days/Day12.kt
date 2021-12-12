package de.nosswald.aoc.days

import de.nosswald.aoc.Day

class Day12: Day(12, "Passage Pathing") {

    private val connections = inputList.flatMap { it.split('-').let { (a, b) -> listOf(a to b, b to a) } }

    private fun List<String>.getNotAllowed() = filter { it.first().isLowerCase() }.let { list ->
        if (list.groupBy { it }.any { it.value.size > 1 }) list else emptyList()
    }

    override fun partOne(): Any {
        var paths = listOf(listOf("start"))

        while (paths.any { it.last() != "end" }) {
            paths = paths.flatMap { path ->
                if (path.last() == "end") return@flatMap listOf(path)
                (connections.filter { it.first == path.last() }
                    .map { it.second } - path.filter { it.first().isLowerCase() }.toSet()).map { path + it }
            }
        }

        return paths.size
    }

    override fun partTwo(): Any {
        var paths = listOf(listOf("start"))

        while (paths.any { it.last() != "end" }) {
            paths = paths.flatMap { path ->
                if (path.last() == "end") return@flatMap listOf(path)
                else (connections.filter { it.first == path.last() }
                    .map { it.second } - path.getNotAllowed().toSet() - listOf("start")).map { path + it }
            }
        }

        return paths.size
    }
}