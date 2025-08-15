package com.example.domain.di.admin.employee

import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.domain.use_cases.admin.employee.GetEmployeesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val adminEmployeeUseCases = module {
    singleOf(::GetEmployeesUseCase)
}