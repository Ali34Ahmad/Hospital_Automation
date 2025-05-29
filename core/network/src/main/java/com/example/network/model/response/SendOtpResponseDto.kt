package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SendOtpResponseDto(
    val message: String
)