package com.example.reset_password.di

import com.example.reset_password.presentation.ResetPasswordViewModel
import com.example.reset_password.validator.ResetPasswordValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val resetPasswordModule = module {
    viewModelOf(::ResetPasswordViewModel)

    singleOf(::ResetPasswordValidator)
}