package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class DeactivateUserAccountUseCase(
    private val employeeAccountManagementRepository:EmployeeAccountManagementRepository
) {
    suspend operator fun invoke(
        deactivateUserAccountRequest: DeactivateUserAccountRequest,
    ): Result<DeactivateUserAccountResponse, rootError> {
        return employeeAccountManagementRepository.deactivateUserAccount(deactivateUserAccountRequest)
    }
}