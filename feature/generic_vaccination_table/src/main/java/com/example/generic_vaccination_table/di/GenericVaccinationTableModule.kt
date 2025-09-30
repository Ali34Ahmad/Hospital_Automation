package com.example.generic_vaccination_table.di

import com.example.generic_vaccination_table.presentation.GenericVaccinationTableViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val genericVaccinationTableModule= module {
    viewModelOf(::GenericVaccinationTableViewModel)
}