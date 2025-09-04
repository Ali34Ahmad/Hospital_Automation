package com.example.domain.repositories.account_management

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.model.account_management.ReactivateUserAccountResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface UserAccountManagementRepository {
    suspend fun deactivateUserAccount(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
    ): Result<DeactivateUserAccountResponse, rootError>

    suspend fun reactivateUserAccount(
        role: Role,
        userId: Int?,
    ): Result<ReactivateUserAccountResponse, rootError>


    suspend fun checkEmployeePermission(
        role: Role,
        ): Result<CheckEmployeePermissionResponse, rootError>

    suspend fun resignUser(
    userId: Int?
    ): Result<DeactivateUserAccountResponse, rootError>

    suspend fun deactivatePharmacyAccount(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
        pharmacyId:Int?,
    ): Result<DeactivateUserAccountResponse, rootError>

    suspend fun reactivatePharmacyAccount(
        pharmacyId:Int?,
    ): Result<ReactivateUserAccountResponse, rootError>

}