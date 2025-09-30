package com.example.domain.repositories.vaccine

import androidx.paging.PagingData
import com.example.model.enums.Role
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.utility.network.Result
import com.example.utility.network.NetworkError
import kotlinx.coroutines.flow.Flow

interface VaccineRepository {
    suspend fun getAllVaccines(): Flow<PagingData<VaccineData>>

    suspend fun getVaccinesWithNoVisitNumber(): Result<List<VaccineMainInfo>, NetworkError>

    suspend fun addNewVaccine(
        vaccineData: VaccineData,
    ): Result<VaccineData, NetworkError>

    suspend fun getVaccineById(
        id: Int,
        role: Role,
    ): Result<VaccineData, NetworkError>

    suspend fun getGenericVaccinationTable(): Result<GenericVaccinationTable, NetworkError>

    suspend fun updateVaccineVisitNumber(
        vaccineIdToVisitNumber: VaccineIdToVisitNumber,
    ): Result<GenericVaccinationTable, NetworkError>


    suspend fun updateVaccinesVisitNumber(
        vaccinesIdsToVisitNumber: VaccinesIdsToVisitNumber,
    ): Result<GenericVaccinationTable, NetworkError>

    suspend fun getChildVaccinationTable(childId:Int): Result<ChildVaccinationTableData, NetworkError>
}