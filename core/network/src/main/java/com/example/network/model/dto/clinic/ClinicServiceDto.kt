package com.example.network.model.dto.clinic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClinicServiceDto(
    @SerialName("Clinic_ServicesId")
    val id: Int,
    val name: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("clinic_id")
    val clinicId: Int
)