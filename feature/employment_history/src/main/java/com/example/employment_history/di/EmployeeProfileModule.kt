package com.example.employment_history.di

import com.example.employment_history.presentation.EmploymentHistoryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employmentHistoryModule = module {
    viewModelOf(::EmploymentHistoryViewModel)
}