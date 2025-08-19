package com.example.network.model.response.vaccine

import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

@Serializable
data class GetAllVaccinesResponseDto(
    val data: List<VaccineDto>,
    val pagination: NetworkPagination
)