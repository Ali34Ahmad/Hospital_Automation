package com.example.domain.use_cases.appointment

import com.example.domain.repositories.AppointmentsRepository
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData

class GetUserAppointmentsUseCase(
    private val repository: AppointmentsRepository
) {
    suspend operator fun invoke(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        dateFilter: String?,
        queryFilter: String?,
        userId: Int,
    ) = repository.getUserAppointment(
        appointmentState = appointmentState,
        onStatisticsChanged = onStatisticsChanged,
        queryFilter = queryFilter,
        dateFilter = dateFilter,
        userId = userId
    )
}