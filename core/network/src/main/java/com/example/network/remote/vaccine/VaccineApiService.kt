package com.example.network.remote.vaccine

import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.vaccine.UpdateVaccinationTableRequestDto
import com.example.network.model.request.vaccine.VaccineIdToVisitNumberDto
import com.example.network.model.request.vaccine.VaccinesIdsToVisitNumberDto
import com.example.network.model.response.vaccine.GetAllVaccinesResponseDto
import com.example.network.model.response.vaccine.VaccineResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface VaccineApiService {
    suspend fun getAllVaccines(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
    ) : Result<GetAllVaccinesResponseDto, NetworkError>

    suspend fun addNewVaccine(
        token: String,
        vaccineRequestDto: VaccineDto,
    ): Result<VaccineResponseDto, rootError>

    suspend fun getVaccineById(
        token: String,
        id: Int,
        role: RoleDto,
    ): Result<VaccineResponseDto, rootError>

    suspend fun getGenericVaccinationTable(
        token: String,
    ): Result<GenericVaccinationTableDto, rootError>

    suspend fun updateVaccineVisitNumber(
        token: String,
        vaccineIdToVisitNumberDto:VaccineIdToVisitNumberDto,
    ): Result<GenericVaccinationTableDto, rootError>

    suspend fun updateVaccinesVisitNumber(
        token: String,
        vaccinesIdsToVisitNumberDto:VaccinesIdsToVisitNumberDto,
    ): Result<GenericVaccinationTableDto, rootError>


}