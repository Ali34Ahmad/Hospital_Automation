package com.example.network.remote.employee

import com.example.network.model.response.doctor.GetEmployeesResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AdminEmployeeApiService {
    suspend fun getEmployees(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError>
}