package com.example.domain.di.prescription

import com.example.domain.use_cases.prescription.AddPrescriptionUseCase
import com.example.domain.use_cases.prescription.GetPrescriptionDetailsUseCase
import com.example.domain.use_cases.prescription.GetPrescriptionsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val prescriptionUseCasesModule = module {
    singleOf(::AddPrescriptionUseCase)

    singleOf(::GetPrescriptionsUseCase)

    singleOf(::GetPrescriptionDetailsUseCase)
}