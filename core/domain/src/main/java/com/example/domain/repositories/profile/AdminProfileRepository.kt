package com.example.domain.repositories.profile

import com.example.model.admin_account.AdminProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AdminProfileRepository {
    suspend fun getAdminInfoById(adminId: Int): Result<AdminProfileResponse, rootError>
}