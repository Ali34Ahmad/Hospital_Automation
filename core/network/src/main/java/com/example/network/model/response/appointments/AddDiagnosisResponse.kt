package com.example.network.model.response.appointments

import kotlinx.serialization.Serializable

@Serializable
data class AddDiagnosisResponse(
    val updatedData: List<Int>
)
