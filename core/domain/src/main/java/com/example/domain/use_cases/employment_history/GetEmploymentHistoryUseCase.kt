package com.example.domain.use_cases.employment_history

import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetEmploymentHistoryUseCase(
    private val employmentHistoryRepository:EmploymentHistoryRepository
) {
    suspend operator fun invoke(id:Int?): Result<EmploymentHistoryResponse, NetworkError> {
        return employmentHistoryRepository.getEmploymentHistory(id)
    }
}