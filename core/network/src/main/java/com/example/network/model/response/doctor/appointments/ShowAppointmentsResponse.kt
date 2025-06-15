package com.example.network.model.response.doctor.appointments

import com.example.network.model.dto.doctor.appointment.AppointmentDto
import com.example.network.model.dto.doctor.appointment.AppointmentStatisticsDto
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
