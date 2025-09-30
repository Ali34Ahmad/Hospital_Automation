package com.example.network.model.dto.appointment

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class AppointmentStatisticsDto(
    @JsonNames("Upcomming_Appointments","upcomming_apps")
    val upcoming: Int  = 0,
    @JsonNames("Passed_Appointments","passed_apps")
    val passed: Int = 0,
    @JsonNames("Missed_Appointments","missed_apps")
    val missed: Int = 0,
)