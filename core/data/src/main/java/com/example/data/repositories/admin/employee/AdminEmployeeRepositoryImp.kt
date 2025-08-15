package com.example.data.repositories.admin.employee

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.paging_sources.employee.EmployeePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.doctor.AdminDoctorRepository
import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.model.admin.EmploymentStatistics
import com.example.model.doctor.DoctorData
import com.example.model.employee.EmployeeData
import com.example.model.employee.EmployeeState
import com.example.network.remote.admin.employee.AdminEmployeeApiService
import kotlinx.coroutines.flow.Flow

class AdminEmployeeRepositoryImp(
    private val adminEmployeeApiService: AdminEmployeeApiService
): AdminEmployeeRepository {
    override suspend fun getEmployees(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics) -> Unit
    ): Flow<PagingData<EmployeeData>> =
        Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE,
            ),
        ){
            EmployeePagingSource(
                token = FAKE_TOKEN,
                query = query,
                status = status,
                onStatisticsUpdated = onStatisticsUpdated,
                adminEmployeeApiService = adminEmployeeApiService
            )
        }.flow
}