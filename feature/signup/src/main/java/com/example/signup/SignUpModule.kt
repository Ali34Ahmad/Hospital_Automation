package com.example.signup

import com.example.signup.main.SignUpViewModel
import com.example.signup.validator.SignUpValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val signUpModule = module {
    viewModelOf(::SignUpViewModel)

    singleOf(::SignUpValidator)
}
