package com.example.network.model.response.appointments

import com.example.utility.network.UpdatedIds
import kotlinx.serialization.Serializable

@Serializable
data class UpdatedStateResponse(
    val updatedState: UpdatedIds
)
