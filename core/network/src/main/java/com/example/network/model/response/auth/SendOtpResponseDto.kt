package com.example.network.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class SendOtpResponseDto(
    val message: String
)