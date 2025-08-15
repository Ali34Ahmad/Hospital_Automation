package com.example.pharmacies_search.di

import com.example.pharmacies_search.presentation.PharmaciesSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pharmaciesSearch = module {
    viewModelOf(::PharmaciesSearchViewModel)
}