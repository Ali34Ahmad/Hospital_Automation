package com.example.network.remote.employee

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.network.model.response.profile.EmployeeProfileResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface EmployeeApiService {
    suspend fun getEmployeeInfo(
        token: String
    ): Result<EmployeeProfileResponseDto, NetworkError>

    suspend fun getEmployeeInfoById(
        token: String,
        id: Int,
        roleDto: RoleDto
    ): Result<GetEmployeeProfileByIdResponseDto, NetworkError>
}