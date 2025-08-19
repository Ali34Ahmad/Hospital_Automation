package com.example.model.medical_record

import com.example.model.user.FullName

data class MedicalRecord(
    val patientId: Int?=null,
    val patientImageUrl: String?,

    val childId: Int?=null,

    val fullName: FullName,

    val numberOfAppointments: Int,
    val numberOfPrescriptions: Int,
)
