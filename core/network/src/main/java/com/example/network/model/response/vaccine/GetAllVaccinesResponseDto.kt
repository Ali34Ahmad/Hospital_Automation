package com.example.network.model.response.vaccine

import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

@Serializable
data class GetAllVaccinesResponseDto(
    val data: List<VaccineResponseDto>,
    val pagination: NetworkPagination
)