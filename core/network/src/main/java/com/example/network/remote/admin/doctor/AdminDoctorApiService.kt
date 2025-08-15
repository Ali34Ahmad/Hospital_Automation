package com.example.network.remote.admin.doctor

import com.example.network.model.response.doctor.GetEmployeesResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AdminDoctorApiService {
    suspend fun getDoctors(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError>

    suspend fun getDoctorsByClinic(
        token: String,
        query: String,
        clinicId: Int,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError>
}