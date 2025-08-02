package com.example.model.helper.ext

import com.example.model.age.Age
import com.example.model.enums.AgeUnit
import java.time.LocalDate
import java.time.Period

fun LocalDate.toAge()
        : Age {
    val birthDate = this
    val today = LocalDate.now()

    val period = Period.between(birthDate, today)
    return when {
        period.years > 0 -> Age(period.years, AgeUnit.YEAR)
        period.months > 0 -> Age(period.months, AgeUnit.MONTH)
        period.days >= 7 -> Age((period.days / 7), AgeUnit.WEEK)
        else -> Age(period.days, AgeUnit.DAY)
    }
}