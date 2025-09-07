package com.example.domain.use_cases.employment_history

import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.model.employment_history.PharmacyContractResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetPharmacyContractUseCase(
    private val employmentHistoryRepository:EmploymentHistoryRepository
) {
    suspend operator fun invoke(pharmacyId:Int): Result<PharmacyContractResponse, rootError> {
        return employmentHistoryRepository.getPharmacyContract(pharmacyId)
    }
}