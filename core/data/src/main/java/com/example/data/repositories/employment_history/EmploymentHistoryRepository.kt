package com.example.data.repositories.employment_history

import com.example.data.mapper.employee.toEmployeeProfileResponse
import com.example.data.mapper.employment_history.toEmploymentHistoryResponse
import com.example.domain.repositories.EmploymentHistoryRepository
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class EmploymentHistoryRepositoryImpl(
    private val employmentHistoryApiService: EmploymentHistoryApiService
):EmploymentHistoryRepository {
    override suspend fun getEmploymentHistory(): Result<EmploymentHistoryResponse, rootError> =
        employmentHistoryApiService.getEmploymentHistory()
            .map { employmentHistoryResponseDto->
                employmentHistoryResponseDto.toEmploymentHistoryResponse()
            }
}