package com.example.domain.use_cases.doctor.clinic

import com.example.domain.repositories.ClinicRepository

data class GetClinicsFlowUseCase(
    private val repo: ClinicRepository
){
    suspend operator fun invoke(
        name: String?
    ) = repo.getAllClinics(name)
}
