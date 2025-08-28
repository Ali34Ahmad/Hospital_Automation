package com.example.domain.use_cases.medical_record

import androidx.paging.PagingData
import com.example.domain.repositories.medical_record.MedicalRecordRepository
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.UserMainInfo
import kotlinx.coroutines.flow.Flow

class GetAllMedicalRecordsUseCase(
    private val medicalRecordRepository: MedicalRecordRepository,
) {
    suspend operator fun invoke(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
    ): Flow<PagingData<MedicalRecord>> {
        return medicalRecordRepository.getAllMedicalRecordsForCurrentDoctor(
            onMainUserInfoChanged=onMainUserInfoChanged
        )
    }
}