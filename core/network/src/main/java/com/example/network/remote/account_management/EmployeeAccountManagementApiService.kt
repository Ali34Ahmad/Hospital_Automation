package com.example.network.remote.account_management

import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.model.response.CheckEmployeePermissionResponseDto
import com.example.network.model.response.DeactivateMyEmployeeAccountResponseDto
import com.example.network.model.response.ReactivateMyEmployeeAccountResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeAccountManagementApiService {
    suspend fun deactivateMyEmployeeAccount(
        deactivateMyEmployeeAccountRequestDto:DeactivateMyEmployeeAccountRequestDto
    ): Result<DeactivateMyEmployeeAccountResponseDto, rootError>

    suspend fun reactivateMyEmployeeAccount(): Result<ReactivateMyEmployeeAccountResponseDto, rootError>

    suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponseDto, rootError>

}