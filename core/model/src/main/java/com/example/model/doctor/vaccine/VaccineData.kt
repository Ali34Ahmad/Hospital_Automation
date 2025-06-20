package com.example.model.doctor.vaccine

import com.example.model.age.Age

data class VaccineData(
    val id: Int,
    val name: String,
    val description: String,
    val quantity: Int,
    val minAge: Age,
    val maxAge: Age
)
