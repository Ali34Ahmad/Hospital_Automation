package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.UserAccountManagementRepository
import com.example.model.account_management.ReactivateUserAccountResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class ReactivatePharmacyUseCase(
    private val userAccountManagementRepository:UserAccountManagementRepository
) {
    suspend operator fun invoke(
        pharmacyId: Int?,
    ): Result<ReactivateUserAccountResponse, NetworkError> {
        return userAccountManagementRepository.reactivatePharmacyAccount(pharmacyId)
    }
}