package com.example.domain.di

import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.employee_account_management.DeactivatePharmacyUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateUserAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivatePharmacyUseCase
import com.example.domain.use_cases.employee_account_management.ResignUserAccountUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val accountManagementUseCasesModule= module {
    singleOf(::DeactivateUserAccountUseCase)

    singleOf(::ReactivateMyAccountUseCase)

    singleOf(::CheckEmployeePermissionUseCase)

    singleOf(::ResignUserAccountUseCase)

    singleOf(::DeactivatePharmacyUseCase)
    singleOf(::ReactivatePharmacyUseCase)
}