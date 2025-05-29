package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponseDto(
    val message: String,
    val updatedData: List<Int>
)