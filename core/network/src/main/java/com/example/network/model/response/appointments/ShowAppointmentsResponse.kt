package com.example.network.model.response.appointments

import com.example.network.model.dto.appointment.AppointmentDto
import com.example.network.model.dto.appointment.AppointmentStatisticsDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowAppointmentsResponse(
    val data: List<AppointmentDto>,
    @SerialName("NumberOfAllAppointments")
    val appointmentStatistics: AppointmentStatisticsDto,
    val pagination: NetworkPagination
)
