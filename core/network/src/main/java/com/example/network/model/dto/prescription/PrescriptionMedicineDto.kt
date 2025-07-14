package com.example.network.model.dto.prescription

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrescriptionMedicineDto(
    @SerialName("medicines_id")
    val medicineId: Int,
    val howToTake: String
)

