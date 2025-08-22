package com.example.network.model.dto.vaccine

import com.example.network.model.enums.AgeUnitDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccineDto(
    @SerialName("vaccinesId")
    val id: Int?=null,

    val name: String,
    val description: String,
    val quantity: Int,

    val minAge: Int,
    val minAgeUnit: AgeUnitDto,

    val maxAge: Int,
    val maxAgeUnit: AgeUnitDto,

    val interactions: List<VaccineInteractionDto>?=null,
)
