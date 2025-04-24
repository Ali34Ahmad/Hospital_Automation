package com.example.model

import java.time.LocalDate

data class Department(
    val id: Int,
    val name: String,
    val workDays: List<WorkDay>,
    val activeDoctors: List<Doctor>,
    val services: List<Service>,
    val providesVaccine: Boolean,
    val creatingDate: LocalDate,
    val isAvailableNow:Boolean,
)
