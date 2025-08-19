package com.example.domain.repositories.medical_record

import androidx.paging.PagingData
import com.example.model.medical_record.MedicalRecord
import kotlinx.coroutines.flow.Flow

interface MedicalRecordRepository {
    suspend fun getAllMedicalRecordsForCurrentDoctor(): Flow<PagingData<MedicalRecord>>
}