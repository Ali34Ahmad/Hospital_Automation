package com.example.network.model.dto.vaccine

import com.example.network.model.enums.VaccineStatusDto
import com.example.network.model.response.user.UserMainInfoDto
import com.example.network.serializer.LocalDateFromTimeZoneSerialize
import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ChildVaccinationTableDto(
    @SerialName("data")
    val visitNumbersToVaccines: List<ChildVaccinationTableVisitDto>
)

@Serializable
data class ChildVaccinationTableVisitDto(
    @SerialName("visit_number")
    val visitNumber: Int,
    @SerialName("vaccines")
    val vaccinesWithVaccinationTableDetails: List<VaccineWithStatusDetailsDto>
)

//@Serializable
//data class VaccineWithVaccinationTableDetailsDto(
//    val vaccineMainInfoDto: VaccineMainInfoDto,
//    val vaccinationTableItemDetailsDto: VaccinationTableItemDetailsDto,
//)

@Serializable
data class VaccineWithStatusDetailsDto(
    @SerialName("vaccinesId")
    val id: Int,
    val name: String,
    @SerialName("vaccinationTables")
    val vaccinationTableDetails:List<VaccinationTableItemDetailsDto>
)

@Serializable
data class VaccinationTableItemDetailsDto(
    @SerialName("vaccinationTableId")
    val vaccinationTableId:Int,

    @SerialName("state")
    val vaccineStatusDto: VaccineStatusDto,

    @Serializable(with = LocalDateFromTimeZoneSerialize::class)
    @SerialName("receiving_date")
    val vaccinationDate: LocalDate?,

    @SerialName("vaccine_provider_id")
    val vaccineProviderId:Int?,


    @Serializable(with = LocalDateSerializer::class)
    @SerialName("next_visit_date")
    val nextVisitDate: LocalDate?,

    val administratedBy: UserMainInfoDto?=null,
    val isAvailableNow:Boolean?=null,
    val appointmentId:Int?=null,
)
