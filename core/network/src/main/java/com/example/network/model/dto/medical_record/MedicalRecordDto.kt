package com.example.network.model.dto.medical_record

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicalRecordDto(
    @SerialName("userId")
    val patientId: Int?=null,
    @SerialName("imgurl")
    val patientImageUrl: String?=null,

    val childId: Int?=null,

    @SerialName("first_name")
    val firstName:String,
    @SerialName("middle_name")
    val middleName:String,
    @SerialName("last_name")
    val lastName:String,

    @SerialName("appointment_count")
    val numberOfAppointments: Int,
    @SerialName("prescription_count")
    val numberOfPrescriptions: Int,
)
