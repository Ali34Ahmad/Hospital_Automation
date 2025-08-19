package com.example.data.repositories.vaccine

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.vaccine.toGenericVaccinationTable
import com.example.data.mapper.vaccine.toUpdateVaccinationTableRequestDto
import com.example.data.mapper.vaccine.toVaccineData
import com.example.data.mapper.vaccine.toVaccineDto
import com.example.data.mapper.vaccine.toVaccineIdToVisitNumberDto
import com.example.data.mapper.vaccine.toVaccinesIdsToVisitNumberDto
import com.example.data.paging_sources.vaccine.VaccinePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.enums.Role
import com.example.model.role_config.RoleAppConfig
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.network.remote.vaccine.VaccineApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError
import kotlinx.coroutines.flow.Flow

class VaccineRepositoryImpl(
    private val vaccineApiService: VaccineApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
) : VaccineRepository {
    override suspend fun getAllVaccines(): Flow<PagingData<VaccineData>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    VaccinePagingSource(
                        token = token,
                        vaccineApi = vaccineApiService,
                        role = roleAppConfig.role.toRoleDto()
                    )
                }
            ).flow
        }


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

    override suspend fun updateVaccineVisitNumber(
        vaccineIdToVisitNumber: VaccineIdToVisitNumber,
    ): Result<GenericVaccinationTable, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.updateVaccineVisitNumber(
                token,
                vaccineIdToVisitNumber.toVaccineIdToVisitNumberDto()
            )
                .map { vaccineData ->
                    vaccineData.toGenericVaccinationTable()
                }
        }

    override suspend fun updateVaccinesVisitNumber(
        vaccinesIdsToVisitNumber: VaccinesIdsToVisitNumber,
    ): Result<GenericVaccinationTable, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            vaccineApiService.updateVaccinesVisitNumber(
                token,
                vaccinesIdsToVisitNumber.toVaccinesIdsToVisitNumberDto()
            )
                .map { vaccineData ->
                    vaccineData.toGenericVaccinationTable()
                }
        }

}