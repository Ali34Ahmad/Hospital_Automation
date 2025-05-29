package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.EmployeeAccountManagementRepository
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class ReactivateMyEmployeeAccountUseCase(
    private val employeeAccountManagementRepository:EmployeeAccountManagementRepository
) {
    suspend operator fun invoke(
    ): Result<ReactivateMyEmployeeAccountResponse, rootError> {
        return employeeAccountManagementRepository.reactivateMyEmployeeAccount()
    }
}