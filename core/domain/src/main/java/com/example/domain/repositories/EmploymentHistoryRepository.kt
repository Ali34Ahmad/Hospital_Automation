package com.example.domain.repositories

import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.enums.Role
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmploymentHistoryRepository {
    suspend fun getEmploymentHistory(role: Role): Result<EmploymentHistoryResponse, rootError>
}