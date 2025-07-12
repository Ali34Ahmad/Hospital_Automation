package com.example.data.di

import com.example.data.repositories.employee_account_management.EmployeeAccountManagementRepositoryImpl
import com.example.data.repositories.user_preferences.UserPreferencesRepositoryImpl
import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val sharedDataModule = module{
    singleOf(::UserPreferencesRepositoryImpl) { bind<UserPreferencesRepository>() }
    singleOf(::EmployeeAccountManagementRepositoryImpl) { bind<EmployeeAccountManagementRepository>() }
}