package com.example.network.model.request.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateVaccinationTableRequestDto(
    val updates:List<VaccinationTableUpdateDto>
)

@Serializable
data class VaccinationTableUpdateDto(
    @SerialName("visit_number")
    val visitNumber:Int?,
    @SerialName("vaccine_id")
    val vaccineId:Int,
)
