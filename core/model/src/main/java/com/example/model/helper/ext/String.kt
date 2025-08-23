package com.example.model.helper.ext

import com.example.model.enums.Gender
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.clickableTextRange(text: String): IntRange {
    val startIndex = this.indexOf(text)
    if (startIndex == -1) {
        return 0..0 // Name not found
    }
    val endIndex = startIndex + text.length - 1
    return startIndex..endIndex
}

fun String.toGenderFromString(): Gender{
    return when{
        this.equals("male",ignoreCase = true) -> Gender.MALE
        else -> Gender.FEMALE
    }
}

fun String.capitalFirstChar():String = lowercase().replaceFirstChar { it.uppercase() }

