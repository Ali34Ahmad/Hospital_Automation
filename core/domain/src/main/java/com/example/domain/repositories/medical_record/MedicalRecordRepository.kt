package com.example.domain.repositories.medical_record

import androidx.paging.PagingData
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.UserMainInfo
import kotlinx.coroutines.flow.Flow

interface MedicalRecordRepository {
    suspend fun getAllMedicalRecordsForCurrentDoctor(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
    ): Flow<PagingData<MedicalRecord>>
}