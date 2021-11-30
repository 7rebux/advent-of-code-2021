package de.nosswald.aoc

abstract class Day(val dayNumber: Int, val title: String) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputString: String by lazy { InputReader.inputAsString(dayNumber) }
    protected val inputList: List<String> by lazy { InputReader.inputAsList(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}