package com.example.model

import com.example.model.doctor.day_scedule.DaySchedule
import java.time.LocalDate

data class Department(
    val id: Int,
    val name: String,
    val workDays: List<DaySchedule>,
    val activeDoctors: List<Doctor>,
    val services: List<Service>,
    val providesVaccine: Boolean,
    val creatingDate: LocalDate,
    val isAvailableNow:Boolean,
    val isDeactivated:Boolean,
    val deactivationReason:String?=null,
    val deactivatedBy:User?=null,
)

data class User(
    val id:Int,
    val name:String,
)