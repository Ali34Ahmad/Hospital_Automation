package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class DeactivateMyAccountUseCase(
    private val employeeAccountManagementRepository:EmployeeAccountManagementRepository
) {
    suspend operator fun invoke(
        deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest,
    ): Result<DeactivateMyEmployeeAccountResponse, rootError> {
        return employeeAccountManagementRepository.deactivateMyEmployeeAccount(deactivateMyEmployeeAccountRequest)
    }
}