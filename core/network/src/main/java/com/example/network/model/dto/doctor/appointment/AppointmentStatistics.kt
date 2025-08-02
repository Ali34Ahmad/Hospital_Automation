package com.example.network.model.dto.doctor.appointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentStatisticsDto(
    @SerialName("Upcomming_Appointments")
    val upcoming: Int  = 0,
    @SerialName("Passed_Appointments")
    val passed: Int = 0,
    @SerialName("Missed_Appointments")
    val missed: Int = 0,
)