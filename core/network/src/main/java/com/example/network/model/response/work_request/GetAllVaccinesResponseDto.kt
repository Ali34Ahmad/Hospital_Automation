package com.example.network.model.response.work_request

import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

@Serializable
data class GetRequestsResponseDto(
    val data: List<SingleRequestResponseDto>,
    val pagination: NetworkPagination
)