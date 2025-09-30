package com.example.enter_email.di

import com.example.enter_email.presentation.EnterEmailViewModel
import com.example.enter_email.validator.EnterEmailValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val enterEmailModule = module {
    viewModelOf(::EnterEmailViewModel)

    singleOf(::EnterEmailValidator)
}