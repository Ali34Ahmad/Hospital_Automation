package com.example.pharmacy_medicines.di

import com.example.pharmacy_medicines.presentation.PharmacyMedicinesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pharmacyMedicinesModule = module {
    viewModelOf(::PharmacyMedicinesViewModel)
}