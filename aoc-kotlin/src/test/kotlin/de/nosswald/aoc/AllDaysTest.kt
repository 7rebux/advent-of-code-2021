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
        Answer({ Day06() }, 349549L, 1589590444365L),
        Answer({ Day07() }, 347011, 98363777),
        Answer({ Day08() }, 272, 1007675),
        Answer({ Day09() }, 588, 964712),
        Answer({ Day10() }, 392139, 4001832844),
        Answer({ Day11() }, 1697, 344),
        Answer({ Day12() }, 5076, 145643),
        Answer({ Day13() }, 755, "BLKJRBAG"),
        Answer({ Day14() }, 2447L, 3018019237563L)
    ).map {
        val day = it.instance()

        DynamicTest.dynamicTest("Day ${day.dayNumber} - ${day.title}") {
            print("Testing Part 1 - Expecting ${it.partOne} ..")
            Assertions.assertEquals(it.partOne, day.partOne())
            print(" SUCCESS\n")

            print("Testing Part 2 - Expecting ${it.partTwo} ..")
            Assertions.assertEquals(it.partTwo, day.partTwo())
            print(" SUCCESS\n")
        }
    }
}
