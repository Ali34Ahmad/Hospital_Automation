package com.example.network.model.enums

import kotlinx.serialization.Serializable

enum class RequestStateDto {
    PENDING,
    REJECTED,
    ACCEPTED
}

@Serializable
data class RequestStateWrapperDto(
    val state: RequestStateDto,
)