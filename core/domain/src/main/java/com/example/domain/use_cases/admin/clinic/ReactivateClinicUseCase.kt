package com.example.domain.use_cases.admin.clinic

import com.example.domain.repositories.admin.clinic.AdminClinicRepository

class ReactivateClinicUseCase(
    private val repository: AdminClinicRepository
) {
    suspend operator fun invoke(clinicId: Int) = repository
        .reactivateClinic(clinicId)
}