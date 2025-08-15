package com.example.domain.use_cases.doctor.appointment

import com.example.domain.repositories.AppointmentsRepository
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData

data class GetAppointmentsFlowUseCase(
    private val appointmentsRepository: AppointmentsRepository
){
    suspend operator fun invoke(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        dateFilter: String?,
        queryFilter: String?,
        doctorId: Int?,
    ) = appointmentsRepository.getAppointments(
        appointmentState = appointmentState,
        onStatisticsChanged = onStatisticsChanged,
        queryFilter = queryFilter,
        dateFilter = dateFilter,
        doctorId = doctorId
    )
}
