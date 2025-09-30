package com.example.child_vaccination_table.di

import com.example.child_vaccination_table.presentation.ChildVaccinationTableViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val childVaccinationTableModule= module {
    viewModelOf(::ChildVaccinationTableViewModel)
}