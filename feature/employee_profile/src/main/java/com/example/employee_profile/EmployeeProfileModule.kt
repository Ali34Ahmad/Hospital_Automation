package com.example.employee_profile

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val employeeProfileModule = module {
    viewModel {
        EmployeeProfileViewModel(
            get(),
            get(),
            get()
        )
    }
}