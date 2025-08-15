package com.example.domain.use_cases.admin.pharmacy

import com.example.domain.repositories.admin.pharmacy.AdminPharmacyRepository
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics

class GetFilteredPharmaciesFlowUseCase(
    private val repository: AdminPharmacyRepository
) {
    suspend operator fun invoke(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics)-> Unit
    ) = repository.getPharmaciesFlow(
        query = query,
        status = status,
        onStatisticsUpdated = onStatisticsUpdated
    )
}