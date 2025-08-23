package com.example.network.model.response.child

import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class NextVaccineDto(
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("next_vaccine")
    val date: LocalDate? = null,
    @SerialName("next_vaccine_id")
    val id: Int? = null
)