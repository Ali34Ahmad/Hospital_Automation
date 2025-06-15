package com.example.domain.use_cases.employee_account_management

import com.example.domain.repositories.EmployeeAccountManagementRepository
import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.rootError

class CheckEmployeePermissionUseCase(
    private val employeeAccountManagementRepository: EmployeeAccountManagementRepository
) {
    suspend operator fun invoke(
        role: Role
    ): Result<CheckEmployeePermissionResponse, rootError> {
        return employeeAccountManagementRepository.checkEmployeePermission(role = role)
    }
}