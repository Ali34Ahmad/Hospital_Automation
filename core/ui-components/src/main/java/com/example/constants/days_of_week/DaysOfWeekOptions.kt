package com.example.constants.days_of_week

import com.example.ext.toCapitalizedString
import com.example.ui_components.components.buttons.Option
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionDetails
import java.time.DayOfWeek

fun getDaysOfWeekOptionsList(): List<Option> {
    return DayOfWeek.entries.toList()
        .map {
            Option(
                titleText = it.name,
            )
        }

}