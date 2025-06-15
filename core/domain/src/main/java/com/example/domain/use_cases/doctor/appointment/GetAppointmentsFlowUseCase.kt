package com.example.domain.use_cases.doctor.appointment

import com.example.domain.repositories.DoctorRepository
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData

data class GetAppointmentsFlowUseCase(
    private val doctorRepository: DoctorRepository
){
    suspend operator fun invoke(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        dateFilter: String?,
        queryFilter: String?,
    ) = doctorRepository.getAppointments(
        appointmentState = appointmentState,
        onStatisticsChanged = onStatisticsChanged,
        queryFilter = queryFilter,
        dateFilter = dateFilter
    )
}
