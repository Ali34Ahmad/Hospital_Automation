package com.example.domain.use_cases.prescription

import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionDetails
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetPrescriptionDetailsUseCase(
    private val prescriptionRepository: PrescriptionRepository
) {
    suspend operator fun invoke(id:Int): Result<PrescriptionDetails, rootError> {
        return prescriptionRepository.getPrescriptionDetailsById(id)
    }
}