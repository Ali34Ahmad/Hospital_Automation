package com.example.doctor_signup.di

import com.example.doctor_signup.presentation.DoctorSignUpViewModel
import com.example.doctor_signup.presentation.validator.DoctorSignUpValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorSignUpModule = module {
    viewModelOf(::DoctorSignUpViewModel)

    singleOf(::DoctorSignUpValidator)
}
