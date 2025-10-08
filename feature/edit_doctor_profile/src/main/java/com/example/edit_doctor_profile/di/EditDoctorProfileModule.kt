package com.example.edit_doctor_profile.di

import com.example.edit_doctor_profile.presentation.EditDoctorProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val editDoctorProfileModule = module {
    viewModelOf(::EditDoctorProfileViewModel)
}