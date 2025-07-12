package com.example.network.model.request.vaccine

import com.example.network.model.dto.vaccine.VaccineInteractionDto
import kotlinx.serialization.Serializable

@Serializable
data class InteractionsWrapper(
    val interactions: List<VaccineInteractionDto>?
)