package com.example.home.di

import com.example.home.presentation.EmployeeHomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employeeHomeModule = module {
    viewModelOf(::EmployeeHomeViewModel)
}