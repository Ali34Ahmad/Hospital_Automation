package com.example.network.model.dto.workday

import com.example.network.serializer.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

@Serializable
data class WorkDaySummaryDto(
    val day: DayOfWeek,
    @Serializable(with = LocalTimeSerializer::class)
    @SerialName("work_start_time")
    val startTime: LocalTime,
    @Serializable(LocalTimeSerializer::class)
    @SerialName("work_end_time")
    val endTime: LocalTime
)