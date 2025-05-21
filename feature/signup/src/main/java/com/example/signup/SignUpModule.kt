package com.example.signup

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    viewModel {
        SignUpViewModel(
            get()
        )
    }
}