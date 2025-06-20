package com.example.network.model.dto.vaccine

import kotlinx.serialization.Serializable

@Serializable
data class VaccineInteractionDto(
    val id: Int?=null,
    val name: String,
    val description: String,
)
