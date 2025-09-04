package com.example.model

import com.example.ext.toAppropriateTimeFormat
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale


data class WorkDayItem(
    val isChecked: Boolean = false,
    val enabled: Boolean = true,
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime = LocalTime.of(8,0,0),
    private val endTime: LocalTime = LocalTime.of(16,0,0),
){
    val dayName: String = dayOfWeek
        .getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)
    val startTimeAsString: String
        get() = startTime.toAppropriateTimeFormat()
    val endTimeAsString: String
        get() = endTime.toAppropriateTimeFormat()
    val timeRange: String
        get() = "$startTimeAsString - $endTimeAsString"
 }


fun main() {
    val sunday = WorkDayItem(dayOfWeek = DayOfWeek.SUNDAY)
    print("$sunday \n ${sunday.dayName} \n ${sunday.startTimeAsString} \n ${sunday.endTimeAsString} ")
}