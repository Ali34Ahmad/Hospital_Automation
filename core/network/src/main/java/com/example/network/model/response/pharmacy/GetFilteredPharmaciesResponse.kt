package com.example.network.model.response.pharmacy

import com.example.network.model.dto.pharmacy.PharmacyDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFilteredPharmaciesResponse(
    val data: List<PharmacyDto>,
    @SerialName("number_of_active_pharmacies")
    val activeCount: Int,
    @SerialName("number_of_stopped_pharmacies")
    val stoppedCount: Int,
    @SerialName("number_of_previous_pharmacies")
    val previousCount: Int,
    val pagination: NetworkPagination
)