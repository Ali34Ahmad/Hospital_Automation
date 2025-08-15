package com.example.domain.repositories.admin.employee

import androidx.paging.PagingData
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeData
import com.example.model.employee.EmployeeState
import kotlinx.coroutines.flow.Flow

interface AdminEmployeeRepository {
    suspend fun getEmployees(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit
    ): Flow<PagingData<EmployeeData>>
}