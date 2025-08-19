package com.example.domain.repositories.vaccine

import androidx.paging.PagingData
import com.example.model.enums.Role
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.utility.network.Result
import com.example.utility.network.rootError
import kotlinx.coroutines.flow.Flow

interface VaccineRepository {
    suspend fun getAllVaccines(): Flow<PagingData<VaccineData>>

    suspend fun addNewVaccine(
        vaccineData: VaccineData,
    ): Result<VaccineData, rootError>

    suspend fun getVaccineById(
        id: Int,
        role: Role,
    ): Result<VaccineData, rootError>

    suspend fun getGenericVaccinationTable(): Result<GenericVaccinationTable, rootError>

    suspend fun updateVaccineVisitNumber(
        vaccineIdToVisitNumber: VaccineIdToVisitNumber,
    ): Result<GenericVaccinationTable, rootError>


    suspend fun updateVaccinesVisitNumber(
        vaccinesIdsToVisitNumber: VaccinesIdsToVisitNumber,
    ): Result<GenericVaccinationTable, rootError>
}