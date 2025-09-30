package com.example.data.repositories.admin_profile

import com.example.data.mapper.admin_profile.toAdminProfileResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.profile.AdminProfileRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.admin_profile.AdminProfileApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.NetworkError

class AdminProfileRepositoryImpl(
    private val adminProfileApiService: AdminProfileApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
) : AdminProfileRepository {
    override suspend fun getAdminInfoById(adminId: Int?): Result<AdminProfileResponse, NetworkError> =
        userPreferencesRepository.executeWithValidToken { token ->
            adminProfileApiService.getAdminInfoById(
                adminId,
                token,
                role = roleAppConfig.role.toRoleDto()
            )
                .map { adminProfileResponseDto ->
                    adminProfileResponseDto.toAdminProfileResponse()
                }
        }
}