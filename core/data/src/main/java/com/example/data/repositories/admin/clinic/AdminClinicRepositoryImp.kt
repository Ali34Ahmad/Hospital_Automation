package com.example.data.repositories.admin.clinic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging_sources.clinic.FilteredClinicPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.clinic.AdminClinicRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.doctor.clinic.ClinicFullData
import com.example.network.remote.clinic.AdminClinicApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

class AdminClinicRepositoryImp(
    private val adminClinicApiService: AdminClinicApiService,
    private val userPreferencesRepository: UserPreferencesRepository
): AdminClinicRepository {
    override suspend fun getClinics(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics) -> Unit,
    ): Flow<PagingData<ClinicFullData>> =
        userPreferencesRepository.executeFlowWithValidToken{token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE,
                )
            ){
                FilteredClinicPagingSource(
                    adminClinicApiService = adminClinicApiService,
                    token = token,
                    query = query,
                    status = status,
                    onStatisticsUpdated = onStatisticsUpdated
                )
            }.flow
        }


    override suspend fun deactivateClinic(
        clinicId: Int,
        deactivationReason: String,
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token ->
            adminClinicApiService.deactivateClinic(
                token = token,
                clinicId = clinicId,
                deactivationReason
            )
        }


    override suspend fun reactivateClinic(
        clinicId: Int
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token ->
            adminClinicApiService.reactivateClinic(
                token = token,
                clinicId = clinicId
            )
        }
}