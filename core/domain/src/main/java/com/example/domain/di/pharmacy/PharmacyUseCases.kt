package com.example.domain.di.pharmacy

import com.example.domain.use_cases.pharmacy.GetPharmaciesByMedicinesIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val pharmacyUseCasesModule = module {
    singleOf(::GetPharmaciesByMedicinesIdUseCase)
}