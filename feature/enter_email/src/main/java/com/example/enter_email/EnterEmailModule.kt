package com.example.enter_email

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val enterEmailModule = module {
    viewModel {
        EnterEmailViewModel(
            get()
        )
    }
}