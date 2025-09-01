package com.example.domain.use_cases.appointment

import com.example.domain.repositories.AppointmentsRepository
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData

class GetChildAppointmentsUseCase(
    private val repository: AppointmentsRepository
) {
    suspend operator fun invoke(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        dateFilter: String?,
        queryFilter: String?,
        childId: Int,
    ) = repository.getChildAppointments(
        appointmentState = appointmentState,
        onStatisticsChanged = onStatisticsChanged,
        queryFilter = queryFilter,
        dateFilter = dateFilter,
        childId = childId
    )
}