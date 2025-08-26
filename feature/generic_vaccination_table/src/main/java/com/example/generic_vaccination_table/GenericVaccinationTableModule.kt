package com.example.generic_vaccination_table

import com.example.generic_vaccination_table.main.GenericVaccinationTableViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val genericVaccinationTableModule= module {
    viewModelOf(::GenericVaccinationTableViewModel)
}