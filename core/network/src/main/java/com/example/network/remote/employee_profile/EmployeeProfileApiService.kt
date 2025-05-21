package com.example.network.remote.employee_profile

import com.example.network.model.response.EmployeeProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeProfileApiService {
    suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, rootError>
}