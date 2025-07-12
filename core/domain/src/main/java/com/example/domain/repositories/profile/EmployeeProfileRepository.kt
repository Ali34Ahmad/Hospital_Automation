package com.example.domain.repositories.profile

import com.example.model.employee.EmployeeProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeProfileRepository {
    suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, rootError>

    suspend fun getEmployeeInfoById(id: Int): Result<EmployeeProfileResponse, rootError>
}