package com.example.network.model.response.vaccine

import com.example.network.model.dto.vaccine.VaccineDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccineResponseDto(
    @SerialName("data")
    val vaccine: VaccineDto,
)

