package com.example.network.model.dto.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccineSummaryDto(
    @SerialName("vaccinesId")
    val vaccineId: Int? = null,
    val name: String
)