package de.nosswald.aoc

import de.nosswald.aoc.days.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val instance: () -> Day, val partOne: Any, val partTwo: Any)

class AllDaysTest {

    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 1215, 1150),
        Answer({ Day02() }, 1636725, 1872757425),
        Answer({ Day03() }, 4138664, 4273224),
        Answer({ Day04() }, 58374, 11377),
        Answer({ Day05() }, 6710, 20121),
        Answer({ Day06() }, 349549, 1589590444365),
        Answer({ Day07() }, 347011, 98363777),
        Answer({ Day08() }, 272, 1007675),
        Answer({ Day09() }, 588, 964712),
        Answer({ Day10() }, 392139, 4001832844),
        Answer({ Day11() }, 1697, 344),
        Answer({ Day12() }, 5076, 145643)
    ).map {
        val day = it.instance()

        DynamicTest.dynamicTest("Day (${day.dayNumber} - ${day.title}) - Part 1 - expecting ${it.partOne}") {
            Assertions.assertEquals(it.partOne, day.partOne())
        }
        DynamicTest.dynamicTest("Day (${day.dayNumber} - ${day.title}) - Part 2 - expecting ${it.partTwo}") {
            Assertions.assertEquals(it.partTwo, day.partTwo())
        }
    }
}