package com.example.domain.di.medical_record

import com.example.domain.use_cases.medical_prescription.GetAllMedicalRecordsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val medicalRecordModule= module {
    singleOf(::GetAllMedicalRecordsUseCase)
}