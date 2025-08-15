package com.example.domain.di.admin.pharmacy

import com.example.domain.use_cases.admin.pharmacy.GetFilteredPharmaciesFlowUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val adminPharmacyUseCases = module{
    singleOf(::GetFilteredPharmaciesFlowUseCase)
}