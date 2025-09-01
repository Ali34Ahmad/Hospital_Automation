package com.example.domain.di

import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateUserAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyAccountUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val accountManagementUseCasesModule= module {
    singleOf(::DeactivateUserAccountUseCase)

    singleOf(::ReactivateMyAccountUseCase)

    singleOf(::CheckEmployeePermissionUseCase)
}