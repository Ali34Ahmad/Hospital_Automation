package com.example.network.model.response.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddNewVaccineResponseDto(
    val vaccine:VaccineResponseDto,
)

@Serializable
data class VaccineResponseDto(
    @SerialName("vaccinesId")
    val id: Int,
)
