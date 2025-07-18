package com.example.medicine_details.di

import com.example.medicine_details.presentation.MedicineDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val medicineDetailsModule = module {
    viewModelOf(::MedicineDetailsViewModel)
}