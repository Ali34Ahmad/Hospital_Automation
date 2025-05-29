package com.example.domain.di

import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateMyEmployeeAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyEmployeeAccountUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employeeAccountManagementUseCasesModule= module {
    singleOf(::DeactivateMyEmployeeAccountUseCase)

    singleOf(::ReactivateMyEmployeeAccountUseCase)

    singleOf(::CheckEmployeePermissionUseCase)
}