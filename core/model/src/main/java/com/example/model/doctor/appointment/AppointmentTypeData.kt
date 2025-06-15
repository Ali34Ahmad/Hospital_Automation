package com.example.model.doctor.appointment

data class AppointmentTypeData(
    val id: Int,
    val name: String,
    val standardDurationInMinutes: Int,
    val description: String,
    val doctorId: Int
)
