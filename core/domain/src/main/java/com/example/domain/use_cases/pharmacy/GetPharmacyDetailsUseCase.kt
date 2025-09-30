package com.example.domain.use_cases.pharmacy

import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetPharmacyDetailsUseCase(
    private val pharmacyRepository: PharmacyRepository
) {
    suspend operator fun invoke(pharmacyId:Int): Result<PharmacyDetailsResponse, NetworkError> {
        return pharmacyRepository.getPharmacyDetailsById(pharmacyId)
    }
}