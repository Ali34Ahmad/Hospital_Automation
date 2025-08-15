package com.example.data.repositories.admin.pharmacy

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.paging_sources.pharmacy.FilteredPharmacyPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.admin.pharmacy.AdminPharmacyRepository
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.pharmacy.PharmacyData
import com.example.network.remote.admin.pharmacy.AdminPharmacyApiService
import kotlinx.coroutines.flow.Flow

class AdminPharmacyRepositoryImp(
    private val adminPharmacyApi: AdminPharmacyApiService
): AdminPharmacyRepository {
    override suspend fun getPharmaciesFlow(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics) -> Unit,
    ): Flow<PagingData<PharmacyData>> =
        Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            )
        ){
            FilteredPharmacyPagingSource(
                adminPharmacyApi = adminPharmacyApi,
                token = FAKE_TOKEN,
                query = query,
                status = status,
                onStatisticsUpdated = onStatisticsUpdated
            )
        }.flow
}