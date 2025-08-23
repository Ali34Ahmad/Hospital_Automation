package com.example.network.model.response

import com.example.utility.network.UpdatedIds
import kotlinx.serialization.Serializable

@Serializable
data class UpdatedData(
    val updatedData: UpdatedIds
)