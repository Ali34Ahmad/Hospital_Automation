package com.example.domain.use_cases.vaccine

import androidx.paging.PagingData
import com.example.domain.repositories.prescription.PrescriptionRepository
import kotlinx.coroutines.flow.Flow
import com.example.model.prescription.PrescriptionWithUser

class GetAllPrescriptionsUseCase(
    private val prescriptionRepository: PrescriptionRepository
) {
    suspend operator fun invoke(): Flow<PagingData<PrescriptionWithUser>> {
        return prescriptionRepository.getAllMedicalPrescriptionsForCurrentDoctor()
    }
}