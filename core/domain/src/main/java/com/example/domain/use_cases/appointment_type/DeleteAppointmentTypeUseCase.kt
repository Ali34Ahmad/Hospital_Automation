package com.example.domain.use_cases.appointment_type

import com.example.domain.repositories.AppointmentTypeRepository

class DeleteAppointmentTypeUseCase(
    private val repository: AppointmentTypeRepository
) {
    suspend operator fun invoke(
        id: Int
    ) = repository.deleteAppointment(
        id = id
    )
}