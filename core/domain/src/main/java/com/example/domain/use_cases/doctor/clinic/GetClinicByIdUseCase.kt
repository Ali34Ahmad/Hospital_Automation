package com.example.domain.use_cases.doctor.clinic

import com.example.domain.repositories.ClinicRepository

data class GetClinicByIdUseCase(
    private val repository: ClinicRepository
){
    suspend operator fun invoke(
        clinicId: Int
    ) = repository.getClinicById(
        clinicId
    )
}
