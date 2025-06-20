package com.example.network.model.dto.vaccine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenericVaccinationTableDto(
    val visits: List<VaccinationTableVisitDto>
)

@Serializable
data class VaccinationTableVisitDto(
    @SerialName("visit_number")
    val visitNumber: Int,
    val vaccines: List<VaccineMainInfoDto>
)

@Serializable
data class VaccineMainInfoDto(
    val id: Int,
    val name: String
)