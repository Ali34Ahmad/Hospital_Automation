package com.example.domain.use_cases.admin.clinic

import com.example.domain.repositories.admin.clinic.AdminClinicRepository

class DeactivateClinicUseCase(
    private val repository: AdminClinicRepository
) {
    suspend operator fun invoke(
        clinicId: Int,
        deactivationReason: String
    ) = repository
        .deactivateClinic(clinicId,deactivationReason)
}