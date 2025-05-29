package com.example.login

import com.example.login.main.LoginViewModel
import com.example.login.validator.LoginValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
    singleOf(::LoginValidator)
}