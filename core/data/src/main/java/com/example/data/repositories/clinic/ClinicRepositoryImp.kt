package com.example.data.repositories.clinic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.doctor.toClinicFullData
import com.example.data.paging_sources.clinic.ClinicPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.ClinicRepository
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.user_preferences.UserPreferencesDataStore
import com.example.network.model.response.clinics.GetClinicByIdResponse
import com.example.network.remote.clinic.ClinicApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow


class ClinicRepositoryImp(
    private val clinicApiService: ClinicApiService,
): ClinicRepository{
    override suspend fun getAllClinics(name: String?): Flow<PagingData<ClinicFullData>> {
        val token = FAKE_TOKEN
        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            ),
        ){
            ClinicPagingSource(
                clinicApiService = clinicApiService,
                token = token,
                name = name
            )
        }.flow
    }

    override suspend fun getClinicById(clinicId: Int): Result<ClinicFullData, NetworkError> {
        val token = FAKE_TOKEN
        return clinicApiService.getClinicById(
            token = token,
            clinicId = clinicId
        ).map {
            it.data.toClinicFullData()
        }
    }
}
