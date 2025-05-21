package com.example.reset_password

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val resetPasswordModule = module {
    viewModel {
        ResetPasswordViewModel(
            get(),
            get()
        )
    }
}