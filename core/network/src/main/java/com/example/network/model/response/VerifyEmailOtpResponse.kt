package com.example.network.model.response

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyEmailOtpResponse(
    val message: String
)