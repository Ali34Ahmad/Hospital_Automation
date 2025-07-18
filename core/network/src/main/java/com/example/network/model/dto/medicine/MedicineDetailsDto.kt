package com.example.network.model.dto.medicine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineDetailsDto(
    @SerialName("medicinesId")
    val medicineId: Int,
    val name: String,
    val pharmaceuticalTiter: Int,
    val pharmaceuticalIndications: String,
    val pharmaceuticalComposition: String,
    val price: Int,
    @SerialName("medImageUrl")
    val imageUrl: String?=null,
    @SerialName("company_Name")
    val companyName: String,
    val isAllowedWithoutPrescription: Boolean,
    @SerialName("alternative")
    val alternatives: List<AlternativeMedicineDto>
)
@Serializable
data class AlternativeMedicineDto(
    val id: Int,
    val medicine: MedicineSummaryDto
)
