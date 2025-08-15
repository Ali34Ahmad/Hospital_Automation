package com.example.domain.repositories.admin.pharmacy

import androidx.paging.PagingData
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.pharmacy.PharmacyData
import kotlinx.coroutines.flow.Flow

interface AdminPharmacyRepository {
    suspend fun getPharmaciesFlow(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics)-> Unit
    ): Flow<PagingData<PharmacyData>>
}