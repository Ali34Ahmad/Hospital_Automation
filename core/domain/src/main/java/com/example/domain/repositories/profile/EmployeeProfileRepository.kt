package com.example.domain.repositories.profile

import com.example.model.employee.EmployeeProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface EmployeeProfileRepository {
    suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, NetworkError>

    suspend fun getEmployeeInfoById(id: Int): Result<EmployeeProfileResponse, NetworkError>
}