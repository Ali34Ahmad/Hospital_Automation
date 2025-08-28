package com.example.network.model.response.work_request

import kotlinx.serialization.Serializable

@Serializable
data class ChangeRequestStateResponseDto(
    val updatedRequest: List<Int>
)
