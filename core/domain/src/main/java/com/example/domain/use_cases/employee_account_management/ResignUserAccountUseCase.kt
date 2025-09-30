package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.UserAccountManagementRepository
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class ResignUserAccountUseCase(
    private val userAccountManagementRepository:UserAccountManagementRepository
) {
    suspend operator fun invoke(
        userId:Int?,
    ): Result<DeactivateUserAccountResponse, NetworkError> {
        return userAccountManagementRepository.resignUser(userId = userId)
    }
}