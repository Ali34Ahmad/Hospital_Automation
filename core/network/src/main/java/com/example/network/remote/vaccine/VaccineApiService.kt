package com.example.network.remote.vaccine

import com.example.network.model.dto.vaccine.ChildVaccinationTableDto
import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.vaccine.VaccineIdToVisitNumberDto
import com.example.network.model.request.vaccine.VaccinesIdsToVisitNumberDto
import com.example.network.model.response.vaccine.GetAllVaccinesResponseDto
import com.example.network.model.response.vaccine.VaccineResponseDto
import com.example.network.model.response.vaccine.VaccinesMainInfoListWrapperDto
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface VaccineApiService {
    suspend fun getAllVaccines(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
    ): Result<GetAllVaccinesResponseDto, NetworkError>

    suspend fun getVaccinesWithNoVisitNumber(
        token: String,
    ): Result<VaccinesMainInfoListWrapperDto, NetworkError>

    suspend fun addNewVaccine(
        token: String,
        vaccineDto: VaccineDto,
    ): Result<VaccineResponseDto, NetworkError>

    suspend fun getVaccineById(
        token: String,
        id: Int,
        role: RoleDto,
    ): Result<VaccineResponseDto, NetworkError>

    suspend fun getGenericVaccinationTable(
        token: String,
        roleDto: RoleDto
    ): Result<GenericVaccinationTableDto, NetworkError>

    suspend fun updateVaccineVisitNumber(
        token: String,
        vaccineIdToVisitNumberDto: VaccineIdToVisitNumberDto,
    ): Result<GenericVaccinationTableDto, NetworkError>

    suspend fun updateVaccinesVisitNumber(
        token: String,
        vaccinesIdsToVisitNumberDto: VaccinesIdsToVisitNumberDto,
    ): Result<GenericVaccinationTableDto, NetworkError>

    suspend fun getChildVaccinationTable(
        token: String,
        childId: Int
    ): Result<ChildVaccinationTableDto, NetworkError>

}