package com.example.constants.days_of_week

import com.maxkeppeler.sheets.option.models.Option
import java.time.DayOfWeek

fun getDaysOfWeekOptionsList(): List<Option> {
    return DayOfWeek.entries.toList()
        .map {
            Option(
                titleText = it.name,
            )
        }

}