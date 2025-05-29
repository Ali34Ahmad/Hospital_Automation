package com.example.model.auth.reset_password

data class ResetPasswordRequest(
    val email: String,
    val password: String
)