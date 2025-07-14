package com.example.data.repositories.pharmacy

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.pharmacy.toPharmacyData
import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.model.pharmacy.PharmacyData
import com.example.network.remote.pharmacy.PharmacyApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map

class PharmacyRepositoryImp(
    private val pharmacyService : PharmacyApiService
): PharmacyRepository {
    override suspend fun getPharmaciesByMedicineId(medicineId: Int): Result<List<PharmacyData>, NetworkError> =
        pharmacyService.getPharmaciesByMedicineId(
            token = FAKE_TOKEN,
            medicineId= medicineId
        ).map {
            it.data.map {
                it.toPharmacyData()
            }
        }

}