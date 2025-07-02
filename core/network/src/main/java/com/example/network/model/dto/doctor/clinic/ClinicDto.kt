package com.example.network.model.dto.doctor.clinic

import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    val clinicId: Int,
    val name: String,
)