package com.example.data.repositories.admin.clinic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.paging_sources.clinic.FilteredClinicPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.clinic.AdminClinicRepository
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.doctor.clinic.ClinicFullData
import com.example.network.model.request.DeactivationReason
import com.example.network.remote.admin.clinic.AdminClinicApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

class AdminClinicRepositoryImp(
    private val adminClinicApiService: AdminClinicApiService
): AdminClinicRepository {
    override suspend fun getClinics(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics) -> Unit,
    ): Flow<PagingData<ClinicFullData>> =
        Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE,
            )
        ){
            FilteredClinicPagingSource(
                adminClinicApiService = adminClinicApiService,
                token = FAKE_TOKEN,
                query = query,
                status = status,
                onStatisticsUpdated = onStatisticsUpdated
            )
        }.flow

    override suspend fun deactivateClinic(
        clinicId: Int,
        deactivationReason: String,
    ): Result<Unit, NetworkError> =
        adminClinicApiService.deactivateClinic(
            token = FAKE_TOKEN,
            clinicId = clinicId,
            deactivationReason
        )

    override suspend fun reactivateClinic(
        clinicId: Int
    ): Result<Unit, NetworkError> =
        adminClinicApiService.reactivateClinic(
            token = FAKE_TOKEN,
            clinicId = clinicId
        )
}