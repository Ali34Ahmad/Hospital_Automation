package com.example.data.repositories.employment_history

import com.example.data.mapper.employment_history.toEmploymentHistoryResponse
import com.example.data.mapper.employment_history.toPharmacyContractResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.employment_history.PharmacyContractResponse
import com.example.model.enums.Role
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.NetworkError

class EmploymentHistoryRepositoryImpl(
    private val employmentHistoryApiService: EmploymentHistoryApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
) : EmploymentHistoryRepository {
    override suspend fun getEmploymentHistory(id: Int?): Result<EmploymentHistoryResponse, NetworkError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employmentHistoryApiService.getEmploymentHistory(
                token = token,
                role = roleAppConfig.role.toRoleDto(),
                id = id,
            )
                .map { employmentHistoryResponseDto ->
                    employmentHistoryResponseDto.toEmploymentHistoryResponse()
                }
        }

    override suspend fun getPharmacyContract(pharmacyId: Int): Result<PharmacyContractResponse, NetworkError> =
        userPreferencesRepository.executeWithValidToken { token ->
            employmentHistoryApiService.getPharmacyContractHistory(
                token = token,
                pharmacyId = pharmacyId,
            )
                .map { pharmacyContractResponseDto ->
                    pharmacyContractResponseDto.toPharmacyContractResponse()
                }
        }
}