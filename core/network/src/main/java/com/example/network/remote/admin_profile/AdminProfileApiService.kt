package com.example.network.remote.admin_profile

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.AdminProfileResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AdminProfileApiService {
    suspend fun getAdminInfoById(
        adminId: Int?,
        token: String,
        role: RoleDto,
    ): Result<AdminProfileResponseDto, rootError>
}