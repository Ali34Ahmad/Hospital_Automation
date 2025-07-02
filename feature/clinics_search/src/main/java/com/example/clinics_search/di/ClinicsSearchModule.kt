package com.example.clinics_search.di

import com.example.clinics_search.presentation.ClinicsSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val clinicsSearchModule = module {
    viewModelOf(::ClinicsSearchViewModel)
}