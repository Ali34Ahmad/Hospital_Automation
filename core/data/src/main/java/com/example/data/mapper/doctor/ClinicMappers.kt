package com.example.data.mapper.doctor

import com.example.data.mapper.day_schedule.toDaySchedule
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.doctor.clinic.ClinicServiceData
import com.example.network.model.dto.clinic.ClinicFullDto
import com.example.network.model.dto.clinic.ClinicServiceDto

fun ClinicFullDto.toClinicFullData() =
    ClinicFullData(
        clinicId = clinicId,
        firstAvailableTime = firstAvailableTime,
        name = name,
        providesVaccines = providesVaccines,
        isDeactivated = isDeactivated,
        deactivationReason = deactivationReason,
        creationDate = creationDate,
        closingDate = closingDate,
        deactivatedById = deactivatedById,
        workDays = workDays.map { it.toDaySchedule() },
        deactivatedByUser = deactivatedByUser?.toDoctorData(),
        activeDoctors = activeDoctors.map { it.toDoctorData() },
        clinicServices = clinicServices.map { it.toClinicFullData() },
    )

fun ClinicServiceDto.toClinicFullData() = ClinicServiceData(
    id = id,
    name = name,
    description = description,
    clinicId = clinicId,
)

