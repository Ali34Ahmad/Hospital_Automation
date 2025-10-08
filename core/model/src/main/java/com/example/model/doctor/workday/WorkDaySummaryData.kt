package com.example.model.doctor.workday

import java.time.DayOfWeek
import java.time.LocalTime

data class WorkDaySummaryData(
    val id: Int,
    val day: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime
)
