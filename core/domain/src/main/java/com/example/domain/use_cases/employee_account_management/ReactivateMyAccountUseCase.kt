package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.rootError

class ReactivateMyAccountUseCase(
    private val employeeAccountManagementRepository:EmployeeAccountManagementRepository
) {
    suspend operator fun invoke(
        role: Role
    ): Result<ReactivateMyEmployeeAccountResponse, rootError> {
        return employeeAccountManagementRepository.reactivateMyEmployeeAccount(role)
    }
}