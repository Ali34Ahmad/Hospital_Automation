package com.example.network.remote.pharmacy

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.pharmacy.GetPharmaciesByMedicineIdResponse
import com.example.network.model.response.pharmacy.PharmacyDetailsResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface PharmacyApiService {
    suspend fun getPharmacyDetailsById(
        token: String,
        id:Int,
        role: RoleDto,
    ) : Result<PharmacyDetailsResponseDto, NetworkError>

    suspend fun getPharmaciesByMedicineId(
        token: String,
        medicineId: Int
    ): Result<GetPharmaciesByMedicineIdResponse, NetworkError>
}