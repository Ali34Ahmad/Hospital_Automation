package com.example.network.remote.admin_profile

import com.example.network.model.response.AdminProfileResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AdminProfileApiService {
    suspend fun getAdminInfoById(adminId: Int): Result<AdminProfileResponseDto, rootError>
}