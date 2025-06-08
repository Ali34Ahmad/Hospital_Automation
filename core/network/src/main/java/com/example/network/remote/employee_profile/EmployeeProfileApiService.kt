package com.example.network.remote.employee_profile

import com.example.network.model.response.EmployeeProfileResponseDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeProfileApiService {
    suspend fun getEmployeeInfo(): Result<EmployeeProfileResponseDto, rootError>

    suspend fun getEmployeeInfoById(id: Int): Result<GetEmployeeProfileByIdResponseDto, rootError>
}