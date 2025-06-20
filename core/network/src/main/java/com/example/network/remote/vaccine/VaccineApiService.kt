package com.example.network.remote.vaccine

import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.vaccine.AddNewVaccineResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface VaccineApiService {
    suspend fun addNewVaccine(
        token: String,
        vaccineRequestDto: VaccineDto,
    ): Result<AddNewVaccineResponseDto, rootError>

    suspend fun getVaccineById(
        token: String,
        id: Int,
        role: RoleDto,
    ): Result<VaccineDto, rootError>

    suspend fun getGenericVaccinationTable(
        token: String,
    ): Result<GenericVaccinationTableDto, rootError>
}