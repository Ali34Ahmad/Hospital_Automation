package com.example.employee_profile

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employeeProfileModule = module {
    viewModelOf(::EmployeeProfileViewModel)
}