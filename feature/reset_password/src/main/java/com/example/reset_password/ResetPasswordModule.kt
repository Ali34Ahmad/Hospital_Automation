package com.example.reset_password

import com.example.reset_password.main.ResetPasswordViewModel
import com.example.reset_password.validator.ResetPasswordValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val resetPasswordModule = module {
    viewModelOf(::ResetPasswordViewModel)

    singleOf(::ResetPasswordValidator)
}