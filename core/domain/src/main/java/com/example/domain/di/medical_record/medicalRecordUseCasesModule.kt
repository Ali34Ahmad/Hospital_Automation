package com.example.domain.di.medical_record

import com.example.domain.use_cases.medical_record.GetAllMedicalRecordsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val medicalRecordUseCasesModule= module {
    singleOf(::GetAllMedicalRecordsUseCase)
}