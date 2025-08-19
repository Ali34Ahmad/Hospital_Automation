package com.example.domain.use_cases.medical_prescription

import androidx.paging.PagingData
import com.example.domain.repositories.medical_record.MedicalRecordRepository
import com.example.model.medical_record.MedicalRecord
import kotlinx.coroutines.flow.Flow

class GetAllMedicalRecordsUseCase(
    private val medicalRecordRepository: MedicalRecordRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MedicalRecord>> {
        return medicalRecordRepository.getAllMedicalRecordsForCurrentDoctor()
    }
}