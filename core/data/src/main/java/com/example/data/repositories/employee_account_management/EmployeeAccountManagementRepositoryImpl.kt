package com.example.data.repositories.employee_account_management

import com.example.data.mapper.employee_management.toCheckEmployeePermissionResponse
import com.example.data.mapper.employee_management.toDeactivateMyEmployeeAccountRequestDto
import com.example.data.mapper.employee_management.toDeactivateMyEmployeeAccountResponse
import com.example.data.mapper.employee_management.toReactivateMyEmployeeAccountResponse
import com.example.domain.repositories.EmployeeAccountManagementRepository
import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class EmployeeAccountManagementRepositoryImpl(
    private val employeeAccountManagementApiService: EmployeeAccountManagementApiService,
) : EmployeeAccountManagementRepository {
    override suspend fun deactivateMyEmployeeAccount(deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest):
            Result<DeactivateMyEmployeeAccountResponse, rootError> =
        employeeAccountManagementApiService.deactivateMyEmployeeAccount(
            deactivateMyEmployeeAccountRequest.toDeactivateMyEmployeeAccountRequestDto()
        )
            .map { deactivateMyEmployeeAccountResponseDto ->
                deactivateMyEmployeeAccountResponseDto.toDeactivateMyEmployeeAccountResponse()
            }

    override suspend fun reactivateMyEmployeeAccount(): Result<ReactivateMyEmployeeAccountResponse, rootError> =
        employeeAccountManagementApiService.reactivateMyEmployeeAccount()
            .map { reactivateMyEmployeeAccountResponseDto ->
                reactivateMyEmployeeAccountResponseDto.toReactivateMyEmployeeAccountResponse()
            }

    override suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponse, rootError> =
        employeeAccountManagementApiService.checkEmployeePermission()
            .map { checkEmployeePermissionResponseDto ->
                checkEmployeePermissionResponseDto.toCheckEmployeePermissionResponse()
            }

}