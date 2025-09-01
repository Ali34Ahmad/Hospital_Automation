package com.example.data.repositories.medical_record

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging_sources.medical_records.MedicalRecordPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.medical_record.MedicalRecordRepository
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.UserMainInfo
import com.example.network.remote.medical_record.MedicalRecordsApiService
import kotlinx.coroutines.flow.Flow

class MedicalRecordRepositoryImpl(
    private val medicalRecordsApiService: MedicalRecordsApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : MedicalRecordRepository {
    override suspend fun getAllMedicalRecordsForCurrentDoctor(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
        name:String?,
    ): Flow<PagingData<MedicalRecord>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    MedicalRecordPagingSource(
                        token = token,
                        medicalRecordsApiService = medicalRecordsApiService,
                        onMainUserInfoChanged=onMainUserInfoChanged,
                        name = name,
                    )
                }
            ).flow
        }

}