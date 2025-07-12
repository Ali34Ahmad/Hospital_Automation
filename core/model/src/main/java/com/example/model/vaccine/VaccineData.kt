package com.example.model.vaccine

import com.example.model.age.Age

data class VaccineData(
    val id: Int?=null,
    val name: String,
    val description: String,
    val quantity: Int,
    val minAge: Age,
    val maxAge: Age,
    val interactions:List<VaccineInteraction>?,
)
