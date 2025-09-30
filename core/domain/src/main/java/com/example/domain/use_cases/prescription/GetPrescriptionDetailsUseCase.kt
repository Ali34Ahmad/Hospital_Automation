package com.example.domain.use_cases.prescription

import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionDetails
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetPrescriptionDetailsUseCase(
    private val prescriptionRepository: PrescriptionRepository
) {
    suspend operator fun invoke(id:Int): Result<PrescriptionDetails, NetworkError> {
        return prescriptionRepository.getPrescriptionDetailsById(id)
    }
}