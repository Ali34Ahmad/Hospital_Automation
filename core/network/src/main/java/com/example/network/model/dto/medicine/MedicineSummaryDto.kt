package com.example.network.model.dto.medicine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineSummaryDto(
    @SerialName("medicinesId")
    val id: Int,
    val name: String,
    val pharmaceuticalTiter: Int,
    @SerialName("medImageUrl")
    val imageUrl: String?=null,
    val price: Int? = null,
)