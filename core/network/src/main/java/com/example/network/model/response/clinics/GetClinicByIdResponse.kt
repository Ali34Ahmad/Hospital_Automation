package com.example.network.model.response.clinics

import com.example.network.model.dto.doctor.clinic.ClinicFullDto
import kotlinx.serialization.Serializable

@Serializable
data class GetClinicByIdResponse(
    val data: ClinicFullDto
)
