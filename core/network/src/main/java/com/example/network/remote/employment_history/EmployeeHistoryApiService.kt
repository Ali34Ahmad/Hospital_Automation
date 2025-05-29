package com.example.network.remote.employment_history

import com.example.network.model.response.EmploymentHistoryResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmploymentHistoryApiService {
    suspend fun getEmploymentHistory(): Result<EmploymentHistoryResponseDto, rootError>
}