package com.example.data.repositories.employee_profile

import com.example.data.mapper.employee_profile.toEmployeeProfileResponse
import com.example.domain.repositories.EmployeeProfileRepository
import com.example.model.employee.EmployeeProfileResponse
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class EmployeeProfileRepositoryImpl(
    private val employeeProfileService: EmployeeProfileApiService,
):EmployeeProfileRepository {
    override suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, rootError> =
        employeeProfileService.getEmployeeInfo()
            .map { employeeProfileResponseDto->
                employeeProfileResponseDto.toEmployeeProfileResponse()
            }

    override suspend fun getEmployeeInfoById(id: Int): Result<EmployeeProfileResponse, rootError> =
        employeeProfileService.getEmployeeInfoById(id)
            .map { employeeProfileByIdResponseDto->
                employeeProfileByIdResponseDto.toEmployeeProfileResponse()
            }
}