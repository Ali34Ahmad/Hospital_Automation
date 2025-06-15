package com.example.network.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailOtpResponseDto(
    val message: String
)