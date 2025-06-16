package com.example.domain.di

import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateMyAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyAccountUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employeeAccountManagementUseCasesModule= module {
    singleOf(::DeactivateMyAccountUseCase)

    singleOf(::ReactivateMyAccountUseCase)

    singleOf(::CheckEmployeePermissionUseCase)
}