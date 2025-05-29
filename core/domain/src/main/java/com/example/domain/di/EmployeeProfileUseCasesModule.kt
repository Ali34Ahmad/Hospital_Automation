package com.example.domain.di

import com.example.domain.use_cases.employee_profile.GetEmployeeProfileUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employeeProfileUseCasesModule= module {
    singleOf(::GetEmployeeProfileUseCase)
}