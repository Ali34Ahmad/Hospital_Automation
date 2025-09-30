package com.example.data.repositories.admin.employee

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging_sources.employee.EmployeePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeData
import com.example.model.employee.EmployeeState
import com.example.network.remote.employee.AdminEmployeeApiService
import kotlinx.coroutines.flow.Flow

class AdminEmployeeRepositoryImp(
    private val adminEmployeeApiService: AdminEmployeeApiService,
    private val userPreferencesRepository: UserPreferencesRepository
): AdminEmployeeRepository {
    override suspend fun getEmployees(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics) -> Unit
    ): Flow<PagingData<EmployeeData>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE,
                ),
            ){
                EmployeePagingSource(
                    token = token,
                    query = query,
                    status = status,
                    onStatisticsUpdated = onStatisticsUpdated,
                    adminEmployeeApiService = adminEmployeeApiService
                )
            }.flow
        }
}