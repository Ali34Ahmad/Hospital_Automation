package com.example.network.model.dto

import com.example.network.serializer.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

@Serializable
data class WorkDayDto(
    @SerialName("work_DaysId")
    val id: Int,
    val day: DayOfWeek,
    @Serializable(with = LocalTimeSerializer::class)
    @SerialName("work_start_time")
    val workStartTime: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    @SerialName("work_end_time")
    val workEndTime: LocalTime,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("pharmacy_id")
    val pharmacyId: Int?,
    @SerialName("doctor_id")
    val doctorId: Int?,
    @SerialName("clinic_id")
    val clinicId: Int?
)