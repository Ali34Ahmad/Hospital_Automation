package com.example.network.model.request

data class VerifyEmailOtpRequest(
    val email: String,
    val otp: String
)