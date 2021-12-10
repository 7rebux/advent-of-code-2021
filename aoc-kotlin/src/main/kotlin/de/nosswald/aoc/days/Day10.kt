package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import java.util.*

class Day10: Day(10, "Syntax Scoring") {

    private val openToClose = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )

    override fun partOne(): Any {
        val open = setOf(
            '(',
            '[',
            '{',
            '<'
        )
        val errorScore = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )
        val stack = Stack<Char>()
        var sum = 0

        inputList.forEach { line ->
            stack.clear()

            run loop@ {
                line.forEach { c ->
                    if (c in open)
                        stack.push(c)
                    else {
                        if (stack.isNotEmpty()) {
                            if (openToClose[stack.pop()] != c) {
                                sum += errorScore[c]!!
                                return@loop
                            }
                        }
                    }
                }
            }
        }

        return sum
    }

    override fun partTwo(): Any {
        val score = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )
        val stack = Stack<Char>()
        val scores = mutableListOf<Long>()

        inputList.forEach { line ->
            stack.clear()

            run loop@ {
                line.forEach { c ->
                    if (c in openToClose.keys)
                        stack.push(c)
                    else
                        if(openToClose[stack.pop()] != c) return@loop
                }

                var lineScore: Long = 0

                while(stack.isNotEmpty())
                    lineScore = lineScore * 5 + score[openToClose[stack.pop()]]!!

                scores.add(lineScore)
            }
        }

        scores.sort()

        return scores[scores.size / 2]
    }
}