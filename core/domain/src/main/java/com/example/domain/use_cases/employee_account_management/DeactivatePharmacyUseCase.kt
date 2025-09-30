package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.UserAccountManagementRepository
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class DeactivatePharmacyUseCase(
    private val userAccountManagementRepository: UserAccountManagementRepository
) {
    suspend operator fun invoke(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
        pharmacyId: Int?,
    ): Result<DeactivateUserAccountResponse, NetworkError> {
        return userAccountManagementRepository.deactivatePharmacyAccount(
            pharmacyId = pharmacyId,
            deactivateUserAccountRequest = deactivateUserAccountRequest,
        )
    }
}