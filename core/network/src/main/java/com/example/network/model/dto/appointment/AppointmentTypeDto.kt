package com.example.network.model.dto.appointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentTypeDto(
    @SerialName("appointment_typesId")
    val id: Int,
    @SerialName("type_name")
    val name: String,
    @SerialName("standard_duration")
    val standardDurationInMinutes: Int,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("doctor_id")
    val doctorId: Int
)