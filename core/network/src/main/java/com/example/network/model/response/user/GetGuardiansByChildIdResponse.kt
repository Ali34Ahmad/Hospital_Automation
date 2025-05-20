package com.example.network.model.response.user

import com.example.network.model.dto.user.UserDto
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class GetGuardiansByChildIdResponse(
    val data : List<UserDto>
)
