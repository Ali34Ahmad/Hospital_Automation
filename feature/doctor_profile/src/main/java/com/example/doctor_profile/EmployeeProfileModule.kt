package com.example.doctor_profile

import com.example.employee_profile.main.EmployeeProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employeeProfileModule = module {
    viewModelOf(::EmployeeProfileViewModel)
}