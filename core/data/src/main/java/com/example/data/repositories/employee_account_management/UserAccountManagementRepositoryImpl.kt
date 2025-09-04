package com.example.data.repositories.employee_account_management

import com.example.data.mapper.employee_management.toCheckEmployeePermissionResponse
import com.example.data.mapper.employee_management.toDeactivateUserAccountRequestDto
import com.example.data.mapper.employee_management.toDeactivateUserAccountResponse
import com.example.data.mapper.employee_management.toReactivateUserAccountResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.account_management.UserAccountManagementRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.model.account_management.ReactivateUserAccountResponse
import com.example.model.enums.Role
import com.example.network.remote.account_management.AccountManagementApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class UserAccountManagementRepositoryImpl(
    private val accountManagementApiService: AccountManagementApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : UserAccountManagementRepository {
    override suspend fun deactivateUserAccount(deactivateUserAccountRequest: DeactivateUserAccountRequest):
            Result<DeactivateUserAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.deactivateUserAccount(
                deactivateUserAccountRequest.toDeactivateUserAccountRequestDto(),
                token = token,
                role = deactivateUserAccountRequest.role.toRoleDto(),
                userId = deactivateUserAccountRequest.userId
            ).map { deactivateUserAccountResponseDto ->
                deactivateUserAccountResponseDto.toDeactivateUserAccountResponse()
            }
        }

    override suspend fun reactivateUserAccount(
        role: Role,
        userId: Int?,
    ): Result<ReactivateUserAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.reactivateUserAccount(
                token = token,
                role = role.toRoleDto(),
                userId = userId,
            )
                .map { reactivateUserAccountResponseDto ->
                    reactivateUserAccountResponseDto.toReactivateUserAccountResponse()
                }
        }

    override suspend fun checkEmployeePermission(
        role: Role,
    ): Result<CheckEmployeePermissionResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.checkEmployeePermission(
                token = token,
                role = role.toRoleDto()
            )
                .map { checkEmployeePermissionResponseDto ->
                    checkEmployeePermissionResponseDto.toCheckEmployeePermissionResponse()
                }
        }

    override suspend fun resignUser(
        userId: Int?
    ): Result<DeactivateUserAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.resignUser(
                token = token,
                userId = userId
            ).map { deactivateUserAccountResponseDto ->
                deactivateUserAccountResponseDto.toDeactivateUserAccountResponse()
            }
        }

    override suspend fun deactivatePharmacyAccount(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
        pharmacyId:Int?,
    ): Result<DeactivateUserAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.deactivatePharmacyAccount(
                token = token,
                deactivateAccountRequestDto = deactivateUserAccountRequest.toDeactivateUserAccountRequestDto(),
                pharmacyId = pharmacyId,
            )
                .map { reactivateUserAccountResponseDto ->
                    reactivateUserAccountResponseDto.toDeactivateUserAccountResponse()
                }
        }


    override suspend fun reactivatePharmacyAccount(
        pharmacyId:Int?,
    ): Result<ReactivateUserAccountResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            accountManagementApiService.reactivatePharmacyAccount(
                token = token,
                pharmacyId = pharmacyId,
            )
                .map { reactivateUserAccountResponseDto ->
                    reactivateUserAccountResponseDto.toReactivateUserAccountResponse()
                }
        }
}