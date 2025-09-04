package com.example.domain.repositories.pharmacy

import com.example.model.pharmacy.PharmacyData
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface PharmacyRepository {
    suspend fun getPharmacyDetailsById(
        pharmacyId:Int
    ) : Result<PharmacyDetailsResponse, rootError>

    suspend fun getPharmaciesByMedicineId(
        medicineId: Int
    ): Result<List<PharmacyData>, NetworkError>
}