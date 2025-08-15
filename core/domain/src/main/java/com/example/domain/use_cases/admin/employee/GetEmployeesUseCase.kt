package com.example.domain.use_cases.admin.employee

import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.DoctorStatus

class GetEmployeesUseCase(
    private val repository: AdminEmployeeRepository
) {
    suspend operator fun invoke(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics) -> Unit
    ) = repository.getEmployees(
        query = query,
        status = status,
        onStatisticsUpdated = onStatisticsUpdated
    )
}