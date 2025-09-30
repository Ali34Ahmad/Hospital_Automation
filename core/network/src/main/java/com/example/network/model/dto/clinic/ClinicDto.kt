package com.example.network.model.dto.clinic

import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    val clinicId: Int,
    val name: String,
)