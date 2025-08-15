package com.example.domain.repositories.admin.clinic

import androidx.paging.PagingData
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.doctor.clinic.ClinicFullData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

interface AdminClinicRepository {
    suspend fun getClinics(
        query: String,
        status: DepartmentState,
        onStatisticsUpdated: (DepartmentStatistics)-> Unit
    ): Flow<PagingData<ClinicFullData>>

    suspend fun deactivateClinic(
        clinicId: Int,
        deactivationReason: String,
    ): Result<Unit, NetworkError>

    suspend fun reactivateClinic(
        clinicId: Int
    ): Result<Unit, NetworkError>


}