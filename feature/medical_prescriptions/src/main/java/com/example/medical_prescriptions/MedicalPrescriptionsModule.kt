package com.example.medical_prescriptions

import com.example.medical_prescriptions.main.MedicalPrescriptionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val medicalPrescriptionsModule= module{
    viewModelOf(::MedicalPrescriptionsViewModel)
}