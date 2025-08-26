package com.example.network.remote.employee_profile

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.EmployeeProfileResponseDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeProfileApiService {
    suspend fun getEmployeeInfo(token: String): Result<EmployeeProfileResponseDto, rootError>

    suspend fun getEmployeeInfoById(token: String,id: Int,roleDto: RoleDto): Result<GetEmployeeProfileByIdResponseDto, rootError>
}