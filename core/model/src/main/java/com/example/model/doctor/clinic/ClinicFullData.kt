package com.example.model.doctor.clinic

import com.example.model.doctor.DoctorData
import com.example.model.doctor.day_scedule.DaySchedule
import java.time.LocalDate
import java.time.LocalTime

data class ClinicFullData(
    val clinicId: Int,
    val firstAvailableTime: LocalTime? = null,
    val name: String,
    val providesVaccines: Boolean,
    val isDeactivated: Boolean,
    val deactivationReason: String? = null,
    val creationDate: LocalDate,
    val closingDate: LocalDate? = null,
    val deactivatedById: Int? = null,
    val workDays: List<DaySchedule>,
    val activeDoctors: List<DoctorData>,
    val deactivatedByUser: DoctorData? = null,
    val clinicServices: List<ClinicServiceData>
)
