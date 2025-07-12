package com.example.domain.use_cases.employee_profile

import com.example.domain.repositories.profile.EmployeeProfileRepository
import com.example.model.employee.EmployeeProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetCurrentEmployeeProfileUseCase(
    private val employeeProfileRepository: EmployeeProfileRepository
) {
    suspend operator fun invoke(): Result<EmployeeProfileResponse, rootError> {
        return employeeProfileRepository.getEmployeeInfo()
    }
}
