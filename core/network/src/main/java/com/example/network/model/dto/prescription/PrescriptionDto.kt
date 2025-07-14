package com.example.network.model.dto.prescription

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrescriptionDto(
    @SerialName("prescriptionsId")
    val id: Int,
    val code: Int,
    val note: String? = null,
    @SerialName("clinic_id")
    val clinicId: Int,
    @SerialName("patient_id")
    val patientId : Int? = null,
    @SerialName("child_id")
    val childId : Int? = null,
    @SerialName("doctor_id")
    val doctorId: Int,
    val updatedAt: String,
    val createdAt: String,
)
