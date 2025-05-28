package com.example.network.model.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("userId")
    val userId: Int? = null,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("middle_name")
    val middleName: String,

    @SerialName("last_name")
    val lastName: String
)
