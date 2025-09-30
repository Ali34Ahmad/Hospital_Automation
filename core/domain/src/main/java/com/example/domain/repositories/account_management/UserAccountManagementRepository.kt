package com.example.domain.repositories.account_management

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.model.account_management.ReactivateUserAccountResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface UserAccountManagementRepository {
    suspend fun deactivateUserAccount(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
    ): Result<DeactivateUserAccountResponse, NetworkError>

    suspend fun reactivateUserAccount(
        role: Role,
        userId: Int?,
    ): Result<ReactivateUserAccountResponse, NetworkError>


    suspend fun checkEmployeePermission(
        role: Role,
        ): Result<CheckEmployeePermissionResponse, NetworkError>

    suspend fun resignUser(
    userId: Int?
    ): Result<DeactivateUserAccountResponse, NetworkError>

    suspend fun deactivatePharmacyAccount(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
        pharmacyId:Int?,
    ): Result<DeactivateUserAccountResponse, NetworkError>

    suspend fun reactivatePharmacyAccount(
        pharmacyId:Int?,
    ): Result<ReactivateUserAccountResponse, NetworkError>

}