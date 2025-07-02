package com.example.clinic_details.di

import com.example.clinic_details.presentation.ClinicDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val clinicDetailsModule = module {
    viewModelOf(::ClinicDetailsViewModel)
}