package com.example.model.auth.login

import com.example.model.enums.Role

data class LoginRequest(
    val email: String,
    val password: String,
    val role: Role,
)