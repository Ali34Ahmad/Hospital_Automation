package com.example.domain.di.prescription

import com.example.domain.use_cases.doctor.prescription.AddPrescriptionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val prescriptionUseCasesModule = module {
    singleOf(::AddPrescriptionUseCase)
}