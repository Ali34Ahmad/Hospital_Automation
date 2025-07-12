package com.example.domain.repositories.vaccine

import com.example.model.enums.Role
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccineData
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface VaccineRepository {
    suspend fun addNewVaccine(
        vaccineData: VaccineData,
    ): Result<VaccineData, rootError>

    suspend fun getVaccineById(
        id: Int,
        role: Role,
    ): Result<VaccineData, rootError>

    suspend fun getGenericVaccinationTable(): Result<GenericVaccinationTable, rootError>

    suspend fun updateGenericVaccinationTable(
        updateVaccinationTableRequest: UpdateVaccinationTableRequest,
    ): Result<GenericVaccinationTable, rootError>

}