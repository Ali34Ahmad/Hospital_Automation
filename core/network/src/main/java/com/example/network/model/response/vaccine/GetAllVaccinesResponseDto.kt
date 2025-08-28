package com.example.network.model.response.vaccine

import com.example.network.model.response.NetworkPagination
import com.example.network.model.response.work_request.SingleRequestResponseDto
import kotlinx.serialization.Serializable

@Serializable
data class GetAllVaccinesResponseDto(
    val data: List<VaccineResponseDto>,
    val pagination: NetworkPagination
)