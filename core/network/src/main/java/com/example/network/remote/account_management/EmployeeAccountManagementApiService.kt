package com.example.network.remote.account_management

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.model.response.profile.CheckPermissionResponseDto
import com.example.network.model.response.profile.DeactivateMyEmployeeAccountResponseDto
import com.example.network.model.response.profile.ReactivateMyEmployeeAccountResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeAccountManagementApiService {
    suspend fun deactivateUserAccount(
        deactivateMyEmployeeAccountRequestDto: DeactivateMyEmployeeAccountRequestDto,
        token: String,
        role: RoleDto,
        userId:Int?,
    ): Result<DeactivateMyEmployeeAccountResponseDto, rootError>

    suspend fun reactivateMyEmployeeAccount(
        token: String,
        role: RoleDto
    ): Result<ReactivateMyEmployeeAccountResponseDto, rootError>

    suspend fun checkEmployeePermission(
        token: String,
        role: RoleDto
    ): Result<CheckPermissionResponseDto, rootError>

}