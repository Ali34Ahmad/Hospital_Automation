package com.example.domain.repositories

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeAccountManagementRepository {
    suspend fun deactivateMyEmployeeAccount(
        deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest
    ): Result<DeactivateMyEmployeeAccountResponse, rootError>

    suspend fun reactivateMyEmployeeAccount(): Result<ReactivateMyEmployeeAccountResponse, rootError>


    suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponse, rootError>

}