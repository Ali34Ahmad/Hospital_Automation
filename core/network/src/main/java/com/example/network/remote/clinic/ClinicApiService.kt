package com.example.network.remote.clinic

import com.example.network.model.response.clinics.GetClinicByIdResponse
import com.example.network.model.response.clinics.GetClinicsResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface ClinicApiService {
    suspend fun getAllClinics(
        token: String,
        page: Int,
        limit: Int,
        name: String?
    ): Result<GetClinicsResponse,NetworkError>

    suspend fun getClinicById(
        token: String,
        clinicId: Int
    ): Result<GetClinicByIdResponse,NetworkError>
}