package com.example.model.prescription

import java.time.LocalDate

data class Prescription(
    val id:Int,
    val patientId: Int? = null,
    val childId: Int? = null,
    val doctorId: Int? = null,

    val code: String,
    val numberOfMedicines: Int?,

    val createdAt: LocalDate,
)
