package com.example.domain.repositories.pharmacy

import com.example.model.pharmacy.PharmacyData
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface PharmacyRepository {
    suspend fun getPharmacyDetailsById(
        pharmacyId:Int
    ) : Result<PharmacyDetailsResponse, NetworkError>

    suspend fun getPharmaciesByMedicineId(
        medicineId: Int
    ): Result<List<PharmacyData>, NetworkError>
}