package com.example.domain.di.clinics

import com.example.domain.use_cases.doctor.clinic.GetClinicByIdUseCase
import com.example.domain.use_cases.doctor.clinic.GetClinicsFlowUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

 val clinicsUseCasesModule = module {
     singleOf(::GetClinicsFlowUseCase)
     singleOf(::GetClinicByIdUseCase)
}