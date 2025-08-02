package com.example.model.doctor.appointment

import com.example.model.child.ChildData
import com.example.model.doctor.clinic.ClinicData
import com.example.model.guardian.GuardianData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class AppointmentData(
    val id: Int,

    val recommendedVisitDate: LocalDate? = null,
    val recommendedVisitNote: String? = null,

    val state: AppointmentState,

    val medicalDiagnosis: String?,
    val date: LocalDate,
    val time: LocalTime,

    val clinicId: Int,

    val vaccineId: Int? = null,

    val appointmentTypeId: Int,

    val childId: Int? = null,

    val doctorId: Int,

    val patientId: Int? = null,

    val appointmentType: AppointmentTypeData,

    val user: GuardianData? = null,

    val vaccine: VaccineSummaryData? = null,

    val clinic: ClinicData,
    val child: ChildData? = null
){
    val dateTime : LocalDateTime
        get() = LocalDateTime.of(date,time)
    val fullName: String
        get() = user?.fullName?:child?.fullName?:"no name"
}