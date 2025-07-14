package com.example.network.remote.pharmacy

import com.example.network.model.response.pharmacy.GetPharmaciesByMedicineIdResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface PharmacyApiService {
    suspend fun getPharmaciesByMedicineId(
        token: String,
        medicineId: Int
    ): Result<GetPharmaciesByMedicineIdResponse, NetworkError>
}