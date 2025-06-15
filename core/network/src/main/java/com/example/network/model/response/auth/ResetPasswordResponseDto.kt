package com.example.network.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponseDto(
    val message: String,
    val updatedData: List<Int>
)