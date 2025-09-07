package com.example.network.model.dto.doctor.appointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentTypeSummaryDto(
    @SerialName("type_name")
    val name: String,
    @SerialName("standard_duration")
    val duration: Int,
    val description: String
)
