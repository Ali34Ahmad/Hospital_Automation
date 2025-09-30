package com.example.pharmacy_details.di

import com.example.pharmacy_details.presentation.PharmacyDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pharmacyDetailsModule = module {
    viewModelOf(::PharmacyDetailsViewModel)
}