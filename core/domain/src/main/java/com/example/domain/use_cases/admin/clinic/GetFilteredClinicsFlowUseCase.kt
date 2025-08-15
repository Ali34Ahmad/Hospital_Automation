package com.example.domain.use_cases.admin.clinic

import com.example.domain.repositories.admin.clinic.AdminClinicRepository
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics

class GetFilteredClinicsFlowUseCase(
    private val repository: AdminClinicRepository
) {
    suspend operator fun invoke(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics)-> Unit
    ) = repository.getClinics(
        query = query,
        status = status,
        onStatisticsUpdated = onStatisticsUpdated
    )
}