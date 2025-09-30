package com.example.prescription_details.di

import com.example.medical_prescription_details.main.PrescriptionDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val prescriptionDetailsModule = module {
    viewModelOf(::PrescriptionDetailsViewModel)
}