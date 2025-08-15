package com.example.data.repositories.medicine

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.doctor.toMedicineDetailsData
import com.example.data.paging_sources.medicine.MedicinePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.MedicineRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.medicine.MedicineData
import com.example.model.medicine.MedicineDetailsData
import com.example.network.remote.medicine.MedicineApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

class MedicineRepositoryImp(
    private val medicineApi: MedicineApiService,
    private val userPreferencesRepository: UserPreferencesRepository
): MedicineRepository{
    override suspend fun getMedicines(name: String?): Flow<PagingData<MedicineData>> {
        return userPreferencesRepository.executeFlowWithValidToken { token->
            Pager(
                config = PagingConfig(pageSize = PagingConstants.PAGE_SIZE),
                pagingSourceFactory = {
                    MedicinePagingSource(
                        token = token,
                        medicineApi = medicineApi,
                        name = name
                    )
                }
            ).flow
        }
    }

    override suspend fun getMedicineById(medicineId: Int): Result<MedicineDetailsData, NetworkError> =
       userPreferencesRepository.executeWithValidTokenNetwork { token->
           medicineApi.getMedicineById(
               token = token,
               medicineId = medicineId
           ).map {response->
               response.data.toMedicineDetailsData()
           }
       }
}
