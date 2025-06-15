package com.example.model.helper.ext

import com.example.model.helper.Age
import com.example.model.helper.AgeUnit
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toCapitalizedString():String = lowercase().replaceFirstChar { it.uppercase() }
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
        Age(value = 0, unit = AgeUnit.NOT_SPECIFIED)
    }

fun String.toAgeFromDate()
    : Age {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDate = LocalDate.parse(this, formatter)
        val today = LocalDate.now()

        val period = Period.between(birthDate, today)
        return when {
            period.years > 0 -> Age(period.years, AgeUnit.YEAR)
            period.months > 0 -> Age(period.months, AgeUnit.MONTH)
            period.days >= 7 -> Age((period.days / 7), AgeUnit.WEEK)
            else -> Age(period.days, AgeUnit.DAY)
        }
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

