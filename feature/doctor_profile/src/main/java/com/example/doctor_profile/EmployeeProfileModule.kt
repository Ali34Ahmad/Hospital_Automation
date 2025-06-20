package com.example.doctor_profile

import com.example.doctor_profile.main.DoctorProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorProfileModule = module {
    viewModelOf(::DoctorProfileViewModel)
}