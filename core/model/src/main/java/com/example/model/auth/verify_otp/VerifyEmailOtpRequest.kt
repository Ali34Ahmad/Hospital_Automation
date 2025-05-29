package com.example.model.auth.verify_otp

data class VerifyEmailOtpRequest(
    val email: String,
    val otp: String
)