package com.example.domain.di.auth.singup

import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.domain.use_cases.auth.sing_up.BaseSignupUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val baseSignUpModule= module {
    singleOf(::BaseSignupUseCase)
}