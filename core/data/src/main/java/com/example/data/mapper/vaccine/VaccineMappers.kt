package com.example.data.mapper.vaccine

import com.example.data.mapper.enums.toAgeUnit
import com.example.data.mapper.enums.toAgeUnitDto
import com.example.data.mapper.user.toUserMainInfo
import com.example.model.age.Age
import com.example.model.enums.VaccineStatus
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.model.vaccine.ChildVaccinationTableVisit
import com.example.model.vaccine.GenericVaccinationTableData
import com.example.model.vaccine.VaccinationTableItemDetails
import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccineInteraction
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccineWithVaccinationTableDetails
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.network.model.dto.vaccine.ChildVaccinationTableDto
import com.example.network.model.dto.vaccine.ChildVaccinationTableVisitDto
import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccinationTableVisitDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.dto.vaccine.VaccineInteractionDto
import com.example.network.model.dto.vaccine.VaccineMainInfoDto
import com.example.network.model.dto.vaccine.VaccineWithStatusDetailsDto
import com.example.network.model.enums.VaccineStatusDto
import com.example.network.model.request.vaccine.VaccineIdToVisitNumberDto
import com.example.network.model.request.vaccine.VaccinesIdsToVisitNumberDto
import com.example.network.model.response.vaccine.VaccineResponseDto

internal fun VaccineResponseDto.toVaccineData() =
    this.vaccine.toVaccineData()

internal fun VaccineDto.toVaccineData() =
    VaccineData(
        id = id,
        name = name,
        description = description,
        quantity = quantity,
        minAge = Age(minAge, minAgeUnit.toAgeUnit()),
        maxAge = Age(minAge, maxAgeUnit.toAgeUnit()),
        interactions = interactions?.map { it.toVaccineInteraction() },
    )

internal fun VaccineData.toVaccineDto() =
    VaccineDto(
        id = id,
        name = name,
        description = description,
        quantity = quantity,
        minAge = minAge.value,
        minAgeUnit = minAge.unit.toAgeUnitDto(),
        maxAge = minAge.value,
        maxAgeUnit = maxAge.unit.toAgeUnitDto(),
        interactions = interactions?.map { it.toVaccineInteractionDto() },
    )


internal fun VaccineInteractionDto.toVaccineInteraction() =
    VaccineInteraction(
        id = this.id,
        name = this.name,
        description = this.description
    )

internal fun VaccineInteraction.toVaccineInteractionDto() =
    VaccineInteractionDto(
        id = this.id,
        name = this.name,
        description = this.description
    )

internal fun GenericVaccinationTableDto.toGenericVaccinationTable() =
    GenericVaccinationTableData(
        visits = this.visits.map { it.toVaccinationTableVisit() }
    )

internal fun VaccinationTableVisitDto.toVaccinationTableVisit() =
    VaccinationTableVisit(
        visitNumber = this.visitNumber,
        vaccines = this.vaccines.map { it.toVaccineMainInfo() }
    )

internal fun VaccineMainInfoDto.toVaccineMainInfo() =
    VaccineMainInfo(
        id = this.id,
        name = this.name
    )

internal fun VaccineIdToVisitNumber.toVaccineIdToVisitNumberDto() =
    VaccineIdToVisitNumberDto(
        visitNumber = this.visitNumber,
        vaccineId = this.vaccineId!!,
    )


internal fun VaccinesIdsToVisitNumber.toVaccinesIdsToVisitNumberDto() =
    VaccinesIdsToVisitNumberDto(
        visitNumber = this.visitNumber,
        vaccinesIds = this.vaccinesIds!!,
    )

internal fun ChildVaccinationTableDto.toChildVaccinationTableData() =
    ChildVaccinationTableData(
        visitNumbersToVaccines = visitNumbersToVaccines.map { it.toChildVaccinationTableVisit() }
    )

internal fun ChildVaccinationTableVisitDto.toChildVaccinationTableVisit() =
    ChildVaccinationTableVisit(
        visitNumber = visitNumber,
        vaccinesWithVaccinationTableDetails = vaccinesWithVaccinationTableDetails.map { it.toVaccineWithVaccinationTableDetails() }
    )

//internal fun VaccineWithVaccinationTableDetailsDto.toVaccineWithVaccinationTableDetails() =
//    VaccineWithVaccinationTableDetails(
//        vaccineMainInfo = vaccineMainInfoDto.toVaccineMainInfo(),
//        vaccinationTableItemDetails = vaccinationTableItemDetailsDto.toVaccinationTableItemDetailsDto()
//    )

internal fun VaccineWithStatusDetailsDto.toVaccineWithVaccinationTableDetails(): VaccineWithVaccinationTableDetails {
    val appointmentId = if (!appointments.isEmpty()) appointments[0].appointmentId
    else null
    return VaccineWithVaccinationTableDetails(
        vaccineMainInfo = VaccineMainInfo(
            id = id,
            name = name
        ),
        vaccinationTableItemDetails = VaccinationTableItemDetails(
            vaccineStatus = vaccinationTableDetails[0].vaccineStatusDto.toVaccineStatus(),
            vaccinationDate = vaccinationTableDetails[0].vaccinationDate,
            administratedBy = vaccinationTableDetails[0].administratedBy?.toUserMainInfo(),
            nextVisitDate = vaccinationTableDetails[0].nextVisitDate,
            isAvailableNow = isAvailableNow==1,
            appointmentId = appointmentId,
        )
    )
}

internal fun VaccineStatusDto.toVaccineStatus() = when (this) {
    VaccineStatusDto.PASSED -> VaccineStatus.PASSED
    VaccineStatusDto.MISSED -> VaccineStatus.MISSED
    VaccineStatusDto.NOT_SPECIFIED -> VaccineStatus.NOT_SPECIFIED
    VaccineStatusDto.UPCOMMING -> VaccineStatus.UPCOMING
}