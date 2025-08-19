package com.example.domain.use_cases.vaccine

import androidx.paging.PagingData
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.VaccineData
import kotlinx.coroutines.flow.Flow

class GetAllVaccinesUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(): Flow<PagingData<VaccineData>> {
        return vaccineRepository.getAllVaccines()
    }
}