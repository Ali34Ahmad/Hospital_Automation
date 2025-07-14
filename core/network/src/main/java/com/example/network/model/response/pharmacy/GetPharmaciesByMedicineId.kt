package com.example.network.model.response.pharmacy

import com.example.network.model.dto.pharmacy.PharmacyDto
import kotlinx.serialization.Serializable

@Serializable
data class GetPharmaciesByMedicineIdResponse(
    val data: List<PharmacyDto>
)
