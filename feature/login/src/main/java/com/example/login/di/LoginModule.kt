package com.example.login.di

import com.example.login.presentation.LoginViewModel
import com.example.login.validator.LoginValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
    singleOf(::LoginValidator)
}