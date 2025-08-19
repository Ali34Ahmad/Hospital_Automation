package com.example.pharmacy_details

import com.example.pharmacy_details.main.PharmacyDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pharmacyDetailsModule = module {
    viewModelOf(::PharmacyDetailsViewModel)
}