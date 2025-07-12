package com.example.data.repositories.employment_history

import com.example.data.mapper.employment_history.toEmploymentHistoryResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.enums.Role
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class EmploymentHistoryRepositoryImpl(
    private val employmentHistoryApiService: EmploymentHistoryApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmploymentHistoryRepository {
    override suspend fun getEmploymentHistory(role: Role): Result<EmploymentHistoryResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employmentHistoryApiService.getEmploymentHistory(
                token = token,
                role = role.toRoleDto(),
            )
                .map { employmentHistoryResponseDto ->
                    employmentHistoryResponseDto.toEmploymentHistoryResponse()
                }
        }
}