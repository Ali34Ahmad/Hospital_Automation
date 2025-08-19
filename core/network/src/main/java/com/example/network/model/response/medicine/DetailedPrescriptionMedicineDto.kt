package com.example.network.model.response.medicine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedPrescriptionMedicineDto(
    @SerialName("prescription_medicinesId")
    val id:Int,
    val medicine: PrescriptionMedicineMainInfoDto,
    val fulfilledBy:Int?,
)

@Serializable
data class PrescriptionMedicineMainInfoDto(
    @SerialName("medicinesId")
    val id:Int,
    val name:String,
    @SerialName("medImageUrl")
    val imgUrl:String?,
    val note:String?=null,
    val titer: Int,
)
