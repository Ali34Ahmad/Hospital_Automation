package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.medicine.MedicineData
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun getMedicines(
        name: String?,
    ): Flow<PagingData<MedicineData>>
}