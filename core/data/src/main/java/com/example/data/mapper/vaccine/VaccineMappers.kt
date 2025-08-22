package com.example.data.mapper.vaccine

import com.example.data.mapper.enums.toAgeUnit
import com.example.data.mapper.enums.toAgeUnitDto
import com.example.model.age.Age
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.model.vaccine.VaccinationTableUpdate
import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccineInteraction
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccinationTableVisitDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.dto.vaccine.VaccineInteractionDto
import com.example.network.model.dto.vaccine.VaccineMainInfoDto
import com.example.network.model.request.vaccine.UpdateVaccinationTableRequestDto
import com.example.network.model.request.vaccine.VaccinationTableUpdateDto
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
        interactions= interactions?.map { it.toVaccineInteraction()},
    )

internal fun VaccineData.toVaccineDto() =
    VaccineDto(
        id = id,
        name = name,
        description = description,
        quantity = quantity,
        minAge =minAge.value,
        minAgeUnit = minAge.unit.toAgeUnitDto(),
        maxAge = minAge.value,
        maxAgeUnit = maxAge.unit.toAgeUnitDto(),
        interactions= interactions?.map { it.toVaccineInteractionDto()},
    )


internal fun VaccineInteractionDto.toVaccineInteraction()=
    VaccineInteraction(
        id = this.id,
        name = this.name,
        description = this.description
    )

internal fun VaccineInteraction.toVaccineInteractionDto()=
    VaccineInteractionDto(
        id = this.id,
        name = this.name,
        description = this.description
    )

internal  fun GenericVaccinationTableDto.toGenericVaccinationTable()=
    GenericVaccinationTable(
        visits = this.visits.map { it.toVaccinationTableVisit() }
    )

internal  fun VaccinationTableVisitDto.toVaccinationTableVisit()=
    VaccinationTableVisit(
        visitNumber = this.visitNumber,
        vaccines = this.vaccines.map { it.toVaccineMainInfo() }
    )

internal  fun VaccineMainInfoDto.toVaccineMainInfo()=
    VaccineMainInfo(
        id = this.id,
        name = this.name
    )

internal  fun UpdateVaccinationTableRequest.toUpdateVaccinationTableRequestDto()=
    UpdateVaccinationTableRequestDto(
        updates = this.updates.map { it.toUpdateVaccinationTableDto() }
    )

internal  fun VaccinationTableUpdate.toUpdateVaccinationTableDto()=
    VaccinationTableUpdateDto(
        visitNumber = this.visitNumber,
        vaccineId = this.vaccineId,
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


