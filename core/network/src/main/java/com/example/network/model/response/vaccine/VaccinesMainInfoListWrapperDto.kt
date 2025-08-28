package com.example.network.model.response.vaccine

import com.example.network.model.dto.vaccine.VaccineMainInfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaccinesMainInfoListWrapperDto(
    @SerialName("data")
    val vaccines: List<VaccineMainInfoDto>
)
