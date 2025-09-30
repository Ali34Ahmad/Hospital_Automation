package com.example.network.remote.clinic

import com.example.network.model.response.clinics.GetFilteredClinicsResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AdminClinicApiService {
    suspend fun getClinics(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetFilteredClinicsResponse, NetworkError>

    suspend fun deactivateClinic(
        token: String,
        clinicId: Int,
        deactivationReason: String,
    ): Result<Unit, NetworkError>

    suspend fun reactivateClinic(
        token: String,
        clinicId: Int
    ): Result<Unit, NetworkError>
}