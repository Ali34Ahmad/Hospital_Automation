package com.example.network.model.response.appointments

import com.example.network.model.dto.appointment.AppointmentDto
import kotlinx.serialization.Serializable

@Serializable
data class ShowAppointmentDetails(
    val data: AppointmentDto
)
