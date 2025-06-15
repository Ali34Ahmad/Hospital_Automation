package com.example.network.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponseDto(
    val message: String
)
