package com.example.domain.di

import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.domain.use_cases.user_preferences.UpdateShowPermissionCardUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userPreferencesUseCasesModule= module {
    singleOf(::GetUserPreferencesUseCase)

    singleOf(::UpdateIsDarkThemeUseCase)

    singleOf(::UpdateShowPermissionCardUseCase)
}