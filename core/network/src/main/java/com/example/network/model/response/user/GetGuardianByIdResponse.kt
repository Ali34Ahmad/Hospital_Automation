package com.example.network.model.response.user

import com.example.network.model.dto.user.UserFullDto
import kotlinx.serialization.Serializable

@Serializable
data class GetGuardianByIdResponse(
    val user: UserFullDto
)
