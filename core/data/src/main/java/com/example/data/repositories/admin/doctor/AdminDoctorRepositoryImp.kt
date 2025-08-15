package com.example.data.repositories.admin.doctor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.paging_sources.doctor.DoctorPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.doctor.AdminDoctorRepository
import com.example.model.admin.EmploymentStatistics
import com.example.model.doctor.DoctorData
import com.example.model.employee.EmployeeState
import com.example.network.remote.admin.doctor.AdminDoctorApiService
import kotlinx.coroutines.flow.Flow

class AdminDoctorRepositoryImp(
    private val adminDoctorApi: AdminDoctorApiService
): AdminDoctorRepository {
    override suspend fun getDoctors(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit
    ): Flow<PagingData<DoctorData>> =
        Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE,
            ),
        ){
            DoctorPagingSource(
                token = FAKE_TOKEN,
                query = query,
                status = status,
                adminDoctorApiService = adminDoctorApi,
                onStatisticsUpdated = onStatisticsUpdated,
                clinicId = null
            )
        }.flow

    override suspend fun getDoctorsByClinic(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics) -> Unit,
        clinicId: Int,
    ): Flow<PagingData<DoctorData>> =
        Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE,
            ),
        ){
            DoctorPagingSource(
                token = FAKE_TOKEN,
                query = query,
                status = status,
                adminDoctorApiService = adminDoctorApi,
                onStatisticsUpdated = onStatisticsUpdated,
                clinicId = clinicId
            )
        }.flow
}