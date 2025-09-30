package com.example.employee_profile.di

import com.example.employee_profile.presentation.EmployeeProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employeeProfileModule = module {
    viewModelOf(::EmployeeProfileViewModel)
}