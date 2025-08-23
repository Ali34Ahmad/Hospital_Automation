package com.example.network.model.response.medicine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedPrescriptionMedicineDto(
    @SerialName("prescription_medicinesId")
    val id: Int,
    val medicine: PrescriptionMedicineMainInfoDto,
    @SerialName("pharmacy_id")
    val fulfilledBy: Int? = null,
    @SerialName("howToTake")
    val note: String? = null,
)

@Serializable
data class PrescriptionMedicineMainInfoDto(
    @SerialName("medicinesId")
    val id: Int,
    val name: String,
    @SerialName("medImageUrl")
    val imgUrl: String?,
    @SerialName("pharmaceuticalTiter")
    val titer: Int? = null,
)
