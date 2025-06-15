package com.example.network.model.dto.doctor

import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    val clinicId: Int,
    val name: String,
)
