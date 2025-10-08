package com.example.domain.use_cases.appointment_type

import com.example.domain.repositories.AppointmentTypeRepository
import com.example.model.doctor.appointment.AppointmentTypeSummaryData

class UpdateAppointmentTypeUseCase(
    private val repository: AppointmentTypeRepository
) {
    suspend operator fun invoke(
        appointmentType: AppointmentTypeSummaryData,
        id: Int
    ) = repository.updateAppointment(
        appointmentType = appointmentType,
    )
}
