package com.example.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequestDto(
    val email: String,
    val password: String
)