package com.example.data.repositories.admin_profile

import com.example.data.mapper.admin_profile.toAdminProfileResponse
import com.example.domain.repositories.AdminProfileRepository
import com.example.model.admin_account.AdminProfileResponse
import com.example.network.remote.admin_profile.AdminProfileApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class AdminProfileRepositoryImpl(
    private val adminProfileApiService: AdminProfileApiService
):AdminProfileRepository {
    override suspend fun getAdminInfoById(adminId: Int): Result<AdminProfileResponse, rootError> =
        adminProfileApiService.getAdminInfoById(adminId)
            .map { adminProfileResponseDto->
                adminProfileResponseDto.toAdminProfileResponse()
            }

}