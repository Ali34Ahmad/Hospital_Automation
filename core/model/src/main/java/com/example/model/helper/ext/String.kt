package com.example.model.helper.ext

import com.example.model.age.Age
import com.example.model.enums.AgeUnit
import com.example.model.enums.Gender
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import java.time.ZoneId
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

fun String.toCapitalizedString():String = lowercase().replaceFirstChar { it.uppercase() }
fun String.toCapitalized():String = lowercase().replaceFirstChar { it.uppercase() }
//converts from data to age
fun String.toAge():Age =
    try {
        val zonedInstant = Instant.parse(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val currentDate = LocalDate.now()

        var value = 0
        var unit = AgeUnit.DAY

        val period = Period.between(zonedInstant,currentDate)

        if(period.years > 0){
            value = period.years
            unit = AgeUnit.YEAR
        }else if(period.months > 0){
            value = period.months
            unit = AgeUnit.MONTH
        }else{
            value = period.days
            unit = AgeUnit.DAY
        }
        Age(value,unit)
    }catch (e: Exception){
        e.printStackTrace()
        Age(value = 0, unit = AgeUnit.NONE)
    }



fun String.toLocalDate(
    pattern: String = "yyyy-MM-dd"
):  LocalDate{
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDate.parse(this, formatter)
}
fun String.toLocalTime(
    pattern: String = "HH:mm:ss"
): LocalTime {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalTime.parse(this, formatter)
}

fun main() {
    val dateTime = LocalDateTime.of(LocalDate.of(2025,10,10), LocalTime.of(10,10,10))
    println(dateTime.format(DateTimeFormatter.ofPattern("dd MMM - hh:ss a",Locale.ENGLISH)))
}

