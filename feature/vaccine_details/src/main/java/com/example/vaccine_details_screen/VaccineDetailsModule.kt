package com.example.vaccine_details_screen

import com.example.vaccine_details_screen.main.VaccineDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vaccineDetailsModule = module {
    viewModelOf(::VaccineDetailsViewModel)
}