package com.example.model.doctor.day_scedule

import java.time.DayOfWeek

data class DaySchedule(
    val id:Int,
    val doctorId:Int?,
    val clinicId:Int?,
    val pharmacyId:Int?,
    val dayOfWeek: DayOfWeek,
    val startTime: String,
    val endTime: String
)