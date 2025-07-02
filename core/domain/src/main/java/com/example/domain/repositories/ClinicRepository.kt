package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.doctor.clinic.ClinicFullData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

interface ClinicRepository {
    suspend fun getAllClinics(
        name: String?
    ): Flow<PagingData<ClinicFullData>>
    suspend fun getClinicById(
        clinicId: Int
    ): Result<ClinicFullData, NetworkError>
}