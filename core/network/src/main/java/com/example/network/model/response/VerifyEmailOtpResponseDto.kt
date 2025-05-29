package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailOtpResponseDto(
    val message: String
)