package com.example.data.repositories.pharmacy

import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.pharmacy.toPharmacyData
import com.example.data.mapper.pharmacy.toPharmacyDetails
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.model.pharmacy.PharmacyData
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.pharmacy.PharmacyApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class PharmacyRepositoryImp(
    private val pharmacyService : PharmacyApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
): PharmacyRepository {
    override suspend fun getPharmacyDetailsById(
        pharmacyId: Int
    ): Result<PharmacyDetailsResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            pharmacyService.getPharmacyDetailsById(
                token = token,
                pharmacyId = pharmacyId,
                roleAppConfig.role.toRoleDto()
            )
                .map { pharmacyDetailsResponse ->
                    pharmacyDetailsResponse.toPharmacyDetails()
                }
        }
    override suspend fun getPharmaciesByMedicineId(medicineId: Int): Result<List<PharmacyData>, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token->
            pharmacyService.getPharmaciesByMedicineId(
                token = token,
                medicineId= medicineId
            ).map {
                it.data.map {
                    it.toPharmacyData()
                }
            }

        }
}