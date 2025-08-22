package com.example.domain.di.medicine

import com.example.domain.use_cases.medicine.GetMedicineByIdUseCase
import com.example.domain.use_cases.medicine.GetMedicinesByPharmacyIdUseCase
import com.example.domain.use_cases.medicine.GetMedicinesFlowUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val medicinesUseCasesModule = module {
    singleOf(::GetMedicinesFlowUseCase)
    singleOf(::GetMedicineByIdUseCase)
    singleOf(::GetMedicinesByPharmacyIdUseCase)
}
