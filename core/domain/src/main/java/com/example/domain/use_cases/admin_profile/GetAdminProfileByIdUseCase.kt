package com.example.domain.use_cases.admin_profile

import com.example.domain.repositories.profile.AdminProfileRepository
import com.example.model.admin_account.AdminProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetAdminProfileByIdUseCase(
    private val adminProfileRepository: AdminProfileRepository
) {
    suspend operator fun invoke(adminId: Int?): Result<AdminProfileResponse, NetworkError> {
        return adminProfileRepository.getAdminInfoById(adminId)
    }
}
