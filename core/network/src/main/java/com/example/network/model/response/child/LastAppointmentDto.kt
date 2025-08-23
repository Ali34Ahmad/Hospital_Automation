package com.example.network.model.response.child

import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class LastAppointmentDto(
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("last_appointment")
    val date: LocalDate? = null,
    @SerialName("last_appointment_id")
    val id: Int? = null
)