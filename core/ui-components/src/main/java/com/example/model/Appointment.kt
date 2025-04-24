package com.example.model

import com.example.constants.enums.AppointmentState
import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
    val id: Int,
    val patientId: Int,
    val patientName:String,
    val doctorId: Int,
    val vaccineName: String? = null,
    val childId: Int? = null,
    val clinicName: String,
    val dateTime: LocalDateTime,
    val appointmentType: String,
    val recommendedVisitDate: LocalDate? = null,
    val recommendedVisitNote: String? = null,
    val state: AppointmentState,
    val medicalDiagnosis: String? = null
)
