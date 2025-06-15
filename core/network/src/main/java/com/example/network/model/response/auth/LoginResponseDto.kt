package com.example.network.model.response.auth

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginResponseDto(
    val message: String,
    val token: String
)