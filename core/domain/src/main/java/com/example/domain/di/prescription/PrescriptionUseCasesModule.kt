package com.example.domain.di.prescription

import com.example.domain.use_cases.doctor.prescription.AddPrescriptionUseCase
import com.example.domain.use_cases.doctor.prescription.GetPrescriptionDetailsUseCase
import com.example.domain.use_cases.vaccine.GetAllPrescriptionsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val prescriptionUseCasesModule = module {
    singleOf(::AddPrescriptionUseCase)

    singleOf(::GetAllPrescriptionsUseCase)

    singleOf(::GetPrescriptionDetailsUseCase)
}