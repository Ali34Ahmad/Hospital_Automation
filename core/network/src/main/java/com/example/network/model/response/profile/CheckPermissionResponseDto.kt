package com.example.network.model.response.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckPermissionResponseDto(
    @SerialName("message")
    val permissionGranted: Boolean,
)
