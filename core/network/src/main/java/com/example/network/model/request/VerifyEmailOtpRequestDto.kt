package com.example.network.model.request

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailOtpRequestDto(
    val email: String,
    val otp: String
)