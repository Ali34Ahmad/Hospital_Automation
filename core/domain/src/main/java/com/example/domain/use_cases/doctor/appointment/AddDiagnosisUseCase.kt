package com.example.domain.use_cases.doctor.appointment

import com.example.domain.repositories.AppointmentsRepository

data class AddDiagnosisUseCase(
    private val repository: AppointmentsRepository
){
    suspend operator fun invoke(
        appointmentId: Int,
        diagnosis: String
    ) = repository.addDiagnosis(
        appointmentId = appointmentId,
        diagnosis = diagnosis.trim()
    )
}
