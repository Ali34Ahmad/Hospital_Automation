package com.example.model.auth.reset_password

import com.example.model.enums.Role

data class ResetPasswordRequest(
    val email: String,
    val password: String,
    val role: Role,
)