package com.example.doctor_profile.di

import com.example.doctor_profile.presentation.DoctorProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorProfileModule = module {
    viewModelOf(::DoctorProfileViewModel)
}