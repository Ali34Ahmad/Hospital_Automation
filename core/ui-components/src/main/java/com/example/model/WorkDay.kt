package com.example.model

import java.time.DayOfWeek
import java.time.LocalTime

data class WorkDay(
    val id: Int?=null,
    val doctorId: Int? = null,
    val clinicId: Int? = null,
    val pharmacyId: Int? = null,
    val day: DayOfWeek,
    val workStartTime: LocalTime,
    val workEndTime: LocalTime,
)