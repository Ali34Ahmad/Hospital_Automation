package com.example.vaccine_details_screen.di

import com.example.vaccine_details_screen.presentation.VaccineDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vaccineDetailsModule = module {
    viewModelOf(::VaccineDetailsViewModel)
}