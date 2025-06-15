package com.example.network.model.request.auth

import com.example.network.model.enums.RoleDto
import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailOtpRequestDto(
    val email: String,
    val otp: String,
)