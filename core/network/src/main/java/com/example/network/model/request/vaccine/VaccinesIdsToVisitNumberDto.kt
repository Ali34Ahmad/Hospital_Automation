package com.example.network.model.request.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccinesIdsToVisitNumberDto(
    @SerialName("visit_number")
    val visitNumber:Int?,
    @SerialName("vaccinesIds")
    val vaccinesIds: List<Int>,
)
