package com.example.medical_diagnosis.di

import com.example.medical_diagnosis.presentation.DiagnosisViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val diagnosisModule = module {
    viewModelOf(::DiagnosisViewModel)
}