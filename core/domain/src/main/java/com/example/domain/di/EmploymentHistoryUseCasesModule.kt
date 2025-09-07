package com.example.domain.di

import com.example.domain.use_cases.employment_history.GetEmploymentHistoryUseCase
import com.example.domain.use_cases.employment_history.GetPharmacyContractUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employmentHistoryUseCasesModule= module {
    singleOf(::GetEmploymentHistoryUseCase)

    singleOf(::GetPharmacyContractUseCase)
}