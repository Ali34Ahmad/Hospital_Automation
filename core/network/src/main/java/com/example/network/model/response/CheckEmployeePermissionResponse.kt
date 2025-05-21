package com.example.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class CheckEmployeePermissionResponse(
    @SerialName("message")
    val permissionGranted: Boolean,
)
