package com.example.data.repositories.pharmacy

import com.example.data.mapper.pharmacy.toPharmacyData
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.model.pharmacy.PharmacyData
import com.example.network.remote.pharmacy.PharmacyApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map

class PharmacyRepositoryImp(
    private val pharmacyService : PharmacyApiService,
    private val userPreferencesRepository: UserPreferencesRepository
): PharmacyRepository {
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