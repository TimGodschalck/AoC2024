package util

import java.io.FileNotFoundException

fun readInput(dayNumber: String) = object {}::class.java
    .getResourceAsStream("/day$dayNumber.txt")
    ?.bufferedReader()
    ?.readLines() ?: throw FileNotFoundException()