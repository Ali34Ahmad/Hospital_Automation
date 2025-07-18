package com.example.network.model.response.medicine

import com.example.network.model.dto.medicine.MedicineDetailsDto
import kotlinx.serialization.Serializable

@Serializable
data class GetMedicineByIdResponse(
    val data: MedicineDetailsDto
)