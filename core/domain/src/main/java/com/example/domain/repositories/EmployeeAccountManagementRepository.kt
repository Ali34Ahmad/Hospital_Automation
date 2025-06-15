package com.example.domain.repositories

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeAccountManagementRepository {
    suspend fun deactivateMyEmployeeAccount(
        deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest,
    ): Result<DeactivateMyEmployeeAccountResponse, rootError>

    suspend fun reactivateMyEmployeeAccount(
        role: Role,
    ): Result<ReactivateMyEmployeeAccountResponse, rootError>


    suspend fun checkEmployeePermission(
        role: Role,
        ): Result<CheckEmployeePermissionResponse, rootError>

}