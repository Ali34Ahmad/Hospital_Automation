package com.example.domain.di

import com.example.domain.use_cases.employee_profile.GetCurrentEmployeeProfileUseCase
import com.example.domain.use_cases.employee_profile.GetEmployeeProfileByIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employeeProfileUseCasesModule= module {
    singleOf(::GetCurrentEmployeeProfileUseCase)

    singleOf(::GetEmployeeProfileByIdUseCase)
}