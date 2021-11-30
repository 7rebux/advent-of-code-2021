@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package de.nosswald.aoc

import java.io.File

object InputReader {

    fun inputAsString(day: Int): String {
        return fromResources(day).readText()
    }

    fun inputAsList(day: Int): List<String> {
        return fromResources(day).readLines()
    }

    private fun fromResources(day: Int): File {
        return File(javaClass.classLoader.getResource(
            "Day${day.toString().padStart(2, '0')}.txt").toURI())
    }
}