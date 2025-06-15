package com.example.network.model.dto.doctor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccineDto(
   @SerialName("vaccinesId")
    val id: Int,

    val name: String,
    val description: String,
    val quantity: Int,

    val minAge: Int,
    val maxAge: Int,

    val minAgeUnit: String,
    val maxAgeUnit: String,

    val createdAt: String,
    val updatedAt: String
)