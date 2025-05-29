package com.example.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeactivateMyEmployeeAccountRequestDto(
    @SerialName("deactivation_reason")
    val deactivationReason: String
)
