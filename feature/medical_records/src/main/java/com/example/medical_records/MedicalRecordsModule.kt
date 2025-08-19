package com.example.medical_records

import com.example.medical_records.main.MedicalRecordsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val medicalRecordsModule= module{
    viewModelOf(::MedicalRecordsViewModel)
}