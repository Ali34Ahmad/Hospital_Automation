package com.example.appointment_details.di

import com.example.appointment_details.presentation.AppointmentDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appointmentDetailsModule = module {
    viewModelOf(::AppointmentDetailsViewModel)
}