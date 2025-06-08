package com.example.domain.use_cases.employee_profile

import com.example.domain.repositories.EmployeeProfileRepository
import com.example.model.employee.EmployeeProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetEmployeeProfileByIdUseCase(
    private val employeeProfileRepository: EmployeeProfileRepository
) {
    suspend operator fun invoke(id: Int): Result<EmployeeProfileResponse, rootError> {
        return employeeProfileRepository.getEmployeeInfoById(id)
    }
}
