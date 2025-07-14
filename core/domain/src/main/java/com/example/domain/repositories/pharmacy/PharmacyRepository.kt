package com.example.domain.repositories.pharmacy

import com.example.model.pharmacy.PharmacyData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface PharmacyRepository {
    suspend fun getPharmaciesByMedicineId(
        medicineId: Int
    ): Result<List<PharmacyData>, NetworkError>
}