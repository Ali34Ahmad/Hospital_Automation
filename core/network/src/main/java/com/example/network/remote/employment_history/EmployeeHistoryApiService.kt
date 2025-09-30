package com.example.network.remote.employment_history

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.EmploymentHistoryResponseDto
import com.example.network.model.response.profile.PharmacyContractHistoryDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface EmploymentHistoryApiService {
    suspend fun getEmploymentHistory(
        token: String,
        role: RoleDto,
        id: Int?
    ): Result<EmploymentHistoryResponseDto, NetworkError>

    suspend fun getPharmacyContractHistory(
        token: String,
        pharmacyId: Int
    ): Result<PharmacyContractHistoryDto, NetworkError>
}