package com.example.data.repositories.medicine

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.doctor.toMedicineDetailsData
import com.example.data.paging_sources.medicine.MedicinePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.MedicineRepository
import com.example.model.medicine.MedicineData
import com.example.model.medicine.MedicineDetailsData
import com.example.network.remote.medicine.MedicineApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

class MedicineRepositoryImp(
    private val medicineApi: MedicineApiService
): MedicineRepository{
    override suspend fun getMedicines(name: String?): Flow<PagingData<MedicineData>> {
        return Pager(
            config = PagingConfig(pageSize = PagingConstants.PAGE_SIZE),
            pagingSourceFactory = {
                MedicinePagingSource(
                    token = FAKE_TOKEN,
                    medicineApi = medicineApi,
                    name = name
                )
            }
        ).flow
    }

    override suspend fun getMedicineById(medicineId: Int): Result<MedicineDetailsData, NetworkError> =
        medicineApi.getMedicineById(
            token = FAKE_TOKEN,
            medicineId = medicineId
        ).map {response->
            response.data.toMedicineDetailsData()
        }
}
