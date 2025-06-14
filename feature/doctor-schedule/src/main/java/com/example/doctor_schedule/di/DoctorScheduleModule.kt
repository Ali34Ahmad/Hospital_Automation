package com.example.doctor_schedule.di

import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorScheduleModule = module {
    viewModelOf(::DoctorScheduleViewModel)
}