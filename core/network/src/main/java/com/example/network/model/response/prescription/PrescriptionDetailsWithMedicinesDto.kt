package com.example.network.model.response.prescription

import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.response.medicine.DetailedPrescriptionMedicineDto
import com.example.network.model.response.user.UserMainInfoDto
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PrescriptionDetailsWithMedicinesDto(
    @SerialName("data")
    val prescriptionDto: PrescriptionDetailsDto,
    @SerialName("medicines")
    val medicinesDto:List<DetailedPrescriptionMedicineDto>,
)

@Serializable
data class PrescriptionDetailsDto(
    @SerialName("prescriptionsId")
    val id: Int,
    @SerialName("patient_id")
    val patientId: Int? = null,
    @SerialName("child_id")
    val childId: Int? = null,
    @SerialName("doctor_id")
    val doctorId: Int? = null,

    val code: String,
    @SerialName("medicines_count")
    val numberOfMedicines: Int?=null,

    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,

    @SerialName("doctorP")
    val doctorMainInfoDto: UserMainInfoDto,
    @SerialName("user")
    val patientMainInfoDto: UserMainInfoDto?=null,
    @SerialName("child")
    val childMainInfoDto: UserMainInfoDto?=null,
)
