package com.example.doctor_schedule.presentation.model

import com.example.model.doctor.appointment.AppointmentState
import java.time.LocalDate

data class AppointmentsFilter(
    val tab: AppointmentState,
    val date: LocalDate?,
    val query: String?,
)
