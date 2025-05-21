package com.example.network.model.request

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyEmailOtpRequest(
    val email: String,
    val otp: String
)