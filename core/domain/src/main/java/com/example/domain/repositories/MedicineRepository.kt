package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.medicine.MedicineData
import com.example.model.medicine.MedicineDetailsData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun getMedicines(
        name: String?,
    ): Flow<PagingData<MedicineData>>

    suspend fun getMedicineById(
        medicineId: Int
    ): Result<MedicineDetailsData, NetworkError>
}