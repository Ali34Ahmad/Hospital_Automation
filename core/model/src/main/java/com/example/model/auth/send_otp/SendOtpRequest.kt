package com.example.model.auth.send_otp

import com.example.model.enums.Role

data class SendOtpRequest(
    val email: String,
    val role: Role,
)
