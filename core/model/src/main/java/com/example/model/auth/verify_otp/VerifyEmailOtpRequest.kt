package com.example.model.auth.verify_otp

import com.example.model.enums.Role

data class VerifyEmailOtpRequest(
    val email: String,
    val otp: String,
    val role: Role,
)