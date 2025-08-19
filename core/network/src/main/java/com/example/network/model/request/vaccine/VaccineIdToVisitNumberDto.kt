package com.example.network.model.request.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccineIdToVisitNumberDto(
    @SerialName("visit_number")
    val visitNumber:Int?,
    @SerialName("vaccinesId")
    val vaccineId:Int,
)
