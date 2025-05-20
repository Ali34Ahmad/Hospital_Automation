package com.example.ext

import android.util.Log
import com.example.constants.enums.AgeUnit
import com.example.constants.enums.Gender
import com.example.model.Age
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
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

fun String.toCapitalizedString():String{
    if (isEmpty()) {
        return this
    }
    return this[0].uppercase() + this.substring(1).lowercase()
}

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
        Log.e("Error",e.message.toString())
        Age(value = 0, unit = AgeUnit.NOT_SPECIFIED)
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
        Date.from(Instant.now())
    }
}