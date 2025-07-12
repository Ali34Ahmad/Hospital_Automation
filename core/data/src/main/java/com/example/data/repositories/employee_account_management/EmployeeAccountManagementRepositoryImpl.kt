package com.example.data.repositories.employee_account_management

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.employee_management.toCheckEmployeePermissionResponse
import com.example.data.mapper.employee_management.toDeactivateMyEmployeeAccountRequestDto
import com.example.data.mapper.employee_management.toDeactivateMyEmployeeAccountResponse
import com.example.data.mapper.employee_management.toReactivateMyEmployeeAccountResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.model.enums.Role
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class EmployeeAccountManagementRepositoryImpl(
    private val employeeAccountManagementApiService: EmployeeAccountManagementApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmployeeAccountManagementRepository {
    override suspend fun deactivateMyEmployeeAccount(deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest):
            Result<DeactivateMyEmployeeAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employeeAccountManagementApiService.deactivateMyEmployeeAccount(
                deactivateMyEmployeeAccountRequest.toDeactivateMyEmployeeAccountRequestDto(),
                token = token,
                role = deactivateMyEmployeeAccountRequest.role.toRoleDto(),
            ).map { deactivateMyEmployeeAccountResponseDto ->
                deactivateMyEmployeeAccountResponseDto.toDeactivateMyEmployeeAccountResponse()
            }
        }


    override suspend fun reactivateMyEmployeeAccount(role: Role): Result<ReactivateMyEmployeeAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employeeAccountManagementApiService.reactivateMyEmployeeAccount(
                token = token,
                role = role.toRoleDto()
            )
                .map { reactivateMyEmployeeAccountResponseDto ->
                    reactivateMyEmployeeAccountResponseDto.toReactivateMyEmployeeAccountResponse()
                }
        }

    override suspend fun checkEmployeePermission(
        role: Role,
    ): Result<CheckEmployeePermissionResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employeeAccountManagementApiService.checkEmployeePermission(token=token,
                role = role.toRoleDto())
                .map { checkEmployeePermissionResponseDto ->
                    checkEmployeePermissionResponseDto.toCheckEmployeePermissionResponse()
                }
        }
}