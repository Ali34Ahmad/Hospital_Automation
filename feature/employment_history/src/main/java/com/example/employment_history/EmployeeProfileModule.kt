package com.example.employment_history

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employmentHistoryModule = module {
    viewModelOf(::EmploymentHistoryViewModel)
}