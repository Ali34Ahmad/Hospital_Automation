package com.example.ext

import com.example.model.enums.Gender
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

fun String.clickableTextRange(text: String): IntRange {
    val startIndex = this.indexOf(text)
    if (startIndex == -1) {
        return 0..0 // Name not found
    }
    val endIndex = startIndex + text.length - 1
    return startIndex..endIndex
}

fun String.toGender(): Gender{
    return when{
        this.equals("male",ignoreCase = true) -> Gender.MALE
        else -> Gender.FEMALE
    }
}

fun String.toDate(): Date {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        Date.from(Instant.now())
    }
}