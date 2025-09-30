package com.example.network.model.response.clinics

import com.example.network.model.dto.clinic.ClinicFullDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFilteredClinicsResponse(
    val data: List<ClinicFullDto>,
    @SerialName("number_of_active_clinics")
    val activeCount: Int,
    @SerialName("number_of_stopped_clinics")
    val stoppedCount: Int,
    @SerialName("number_of_previous_clinics")
    val previousCount: Int,
    val pagination: NetworkPagination
)