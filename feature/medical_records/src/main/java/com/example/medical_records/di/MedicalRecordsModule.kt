package com.example.medical_records.di

import com.example.medical_records.presentation.MedicalRecordsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val medicalRecordsModule= module{
    viewModelOf(::MedicalRecordsViewModel)
}