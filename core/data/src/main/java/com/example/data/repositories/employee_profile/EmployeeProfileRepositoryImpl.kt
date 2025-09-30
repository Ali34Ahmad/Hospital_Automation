package com.example.data.repositories.employee_profile

import com.example.data.mapper.employee_profile.toEmployeeProfileResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.profile.EmployeeProfileRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.employee.EmployeeApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.NetworkError

class EmployeeProfileRepositoryImpl(
    private val employeeProfileService: EmployeeApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
) : EmployeeProfileRepository {
    override suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, NetworkError> =
        userPreferencesRepository.executeWithValidToken {token->
            employeeProfileService.getEmployeeInfo(token)
                .map { employeeProfileResponseDto ->
                    employeeProfileResponseDto.toEmployeeProfileResponse()
                }
        }

    override suspend fun getEmployeeInfoById(id: Int): Result<EmployeeProfileResponse, NetworkError> =
        userPreferencesRepository.executeWithValidToken {token->
            employeeProfileService.getEmployeeInfoById(token,id,roleAppConfig.role.toRoleDto())
                .map { employeeProfileByIdResponseDto ->
                    employeeProfileByIdResponseDto.toEmployeeProfileResponse()
                }
        }
}