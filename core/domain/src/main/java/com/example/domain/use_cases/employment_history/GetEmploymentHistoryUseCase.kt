package com.example.domain.use_cases.employment_history

import com.example.domain.repositories.EmploymentHistoryRepository
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetEmploymentHistoryUseCase(
    private val employmentHistoryRepository:EmploymentHistoryRepository
) {
    suspend operator fun invoke(): Result<EmploymentHistoryResponse, rootError> {
        return employmentHistoryRepository.getEmploymentHistory()
    }
}