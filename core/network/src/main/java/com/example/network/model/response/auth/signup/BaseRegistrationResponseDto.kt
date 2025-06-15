package com.example.network.model.response.auth.signup

import com.example.network.model.response.user.UserDataDto
import kotlinx.serialization.Serializable

@Serializable
data class BaseRegistrationResponseDto(
    val data: UserDataDto
)
