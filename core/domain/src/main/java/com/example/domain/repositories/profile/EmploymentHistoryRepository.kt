package com.example.domain.repositories.profile

import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.employment_history.PharmacyContractResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface EmploymentHistoryRepository {
    suspend fun getEmploymentHistory(id:Int?): Result<EmploymentHistoryResponse, NetworkError>

    suspend fun getPharmacyContract(pharmacyId:Int): Result<PharmacyContractResponse, NetworkError>
}