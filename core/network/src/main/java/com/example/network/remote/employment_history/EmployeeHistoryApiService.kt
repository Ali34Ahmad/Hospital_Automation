package com.example.network.remote.employment_history

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.EmploymentHistoryResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmploymentHistoryApiService {
    suspend fun getEmploymentHistory(token: String,role: RoleDto): Result<EmploymentHistoryResponseDto, rootError>
}