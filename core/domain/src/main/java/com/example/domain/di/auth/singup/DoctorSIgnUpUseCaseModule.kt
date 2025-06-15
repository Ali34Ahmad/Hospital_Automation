package com.example.domain.di.auth.singup

import com.example.domain.use_cases.auth.sing_up.DoctorSignupUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorSignUpModule= module {
    singleOf(::DoctorSignupUseCase)
}