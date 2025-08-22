package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildFullDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ChildFullResponse(
    val child: ChildFullDto,
    @SerialName("numOfGuardian")
    val numOfGuardians: Int,
    @SerialName("last_appointment")
    val lastAppointment: LastAppointmentDto? = null,
    @SerialName("last_vaccine")
    val lastVaccination: LastVaccineDto? = null,
    @SerialName("next_vaccine")
    val nextVaccination: NextVaccineDto? = null
)






