package com.example.hospital_automation.app_logic

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AppViewModel(
            get()
        )
    }
}