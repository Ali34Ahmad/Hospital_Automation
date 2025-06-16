package com.example.doctor_signup

import com.example.doctor_signup.main.DoctorSignUpViewModel
import com.example.doctor_signup.main.validator.DoctorSignUpValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorSignUpModule = module {
    viewModelOf(::DoctorSignUpViewModel)

    singleOf(::DoctorSignUpValidator)
}
