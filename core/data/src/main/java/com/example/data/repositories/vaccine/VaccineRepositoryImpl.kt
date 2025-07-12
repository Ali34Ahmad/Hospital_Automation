package com.example.data.repositories.vaccine

import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.vaccine.toGenericVaccinationTable
import com.example.data.mapper.vaccine.toUpdateVaccinationTableRequestDto
import com.example.data.mapper.vaccine.toVaccineData
import com.example.data.mapper.vaccine.toVaccineDto
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.enums.Role
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccineData
import com.example.network.remote.vaccine.VaccineApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class VaccineRepositoryImpl(
    private val vaccineApiService: VaccineApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : VaccineRepository {
    override suspend fun addNewVaccine(
        vaccineData: VaccineData
    ): Result<VaccineData, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.addNewVaccine(
                token,
                vaccineData.toVaccineDto()
            )
                .map { vaccineData ->
                    vaccineData.toVaccineData()
                }
        }

    override suspend fun getVaccineById(
        id: Int,
        role: Role
    ): Result<VaccineData, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.getVaccineById(
                token = token,
                id = id,
                role = role.toRoleDto()
            )
                .map { vaccineData ->
                    vaccineData.toVaccineData()
                }
        }

    override suspend fun getGenericVaccinationTable():
            Result<GenericVaccinationTable, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.getGenericVaccinationTable(
                token,
            )
                .map { genericVaccinationTable ->
                    genericVaccinationTable.toGenericVaccinationTable()
                }
        }

    override suspend fun updateGenericVaccinationTable(
        updateVaccinationTableRequest: UpdateVaccinationTableRequest
    ): Result<GenericVaccinationTable, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.updateGenericVaccinationTable(
                token,
                updateVaccinationTableRequest.toUpdateVaccinationTableRequestDto()
            )
                .map { vaccineData ->
                    vaccineData.toGenericVaccinationTable()
                }
        }
}