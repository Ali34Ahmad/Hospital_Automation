package com.example.network.remote.account_management

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.DeactivateAccountRequestDto
import com.example.network.model.response.profile.CheckPermissionResponseDto
import com.example.network.model.response.profile.DeactivateUserAccountResponseDto
import com.example.network.model.response.profile.ReactivateUserAccountResponseDto
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface AccountManagementApiService {
    suspend fun deactivateUserAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        role: RoleDto,
        userId:Int?,
    ): Result<DeactivateUserAccountResponseDto, NetworkError>

    suspend fun reactivateUserAccount(
        token: String,
        role: RoleDto,
        userId:Int?,
    ): Result<ReactivateUserAccountResponseDto, NetworkError>

    suspend fun checkEmployeePermission(
        token: String,
        role: RoleDto
    ): Result<CheckPermissionResponseDto, NetworkError>

    suspend fun resignUser(
        token: String,
        userId: Int?
    ): Result<DeactivateUserAccountResponseDto, NetworkError>

    suspend fun deactivatePharmacyAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        pharmacyId:Int?,
    ): Result<DeactivateUserAccountResponseDto, NetworkError>

    suspend fun reactivatePharmacyAccount(
        token: String,
        pharmacyId:Int?,
    ): Result<ReactivateUserAccountResponseDto, NetworkError>

}