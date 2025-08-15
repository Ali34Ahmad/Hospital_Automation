package com.example.domain.di.admin.clinic

import com.example.domain.use_cases.admin.clinic.DeactivateClinicUseCase
import com.example.domain.use_cases.admin.clinic.GetFilteredClinicsFlowUseCase
import com.example.domain.use_cases.admin.clinic.ReactivateClinicUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val adminClinicUseCases = module {
    singleOf(::GetFilteredClinicsFlowUseCase)
    singleOf(::DeactivateClinicUseCase)
    singleOf(::ReactivateClinicUseCase)
}