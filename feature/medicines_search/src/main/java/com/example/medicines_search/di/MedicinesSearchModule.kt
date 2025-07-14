package com.example.medicines_search.di

import com.example.medicines_search.presentation.MedicineSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val medicinesSearchModule = module{
    viewModelOf(::MedicineSearchViewModel)
}