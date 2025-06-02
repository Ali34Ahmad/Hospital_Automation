package com.example.employment_history

import com.example.employment_history.main.EmploymentHistoryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employmentHistoryModule = module {
    viewModelOf(::EmploymentHistoryViewModel)
}