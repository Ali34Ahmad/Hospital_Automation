package com.example.network.model.request.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeactivateUserRequest(
    val state : String,
    @SerialName("suspending_reason")
    val suspendingReason: String,
)