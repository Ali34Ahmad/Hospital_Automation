package com.example.network.model.response.clinics

import com.example.network.model.dto.doctor.clinic.ClinicFullDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

@Serializable
data class GetClinicsResponse(
    val data: List<ClinicFullDto>,
    val pagination: NetworkPagination
)
