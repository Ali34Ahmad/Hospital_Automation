package com.example.domain.use_cases.user_preferences

import com.example.domain.repositories.local.UserPreferencesRepository

class UpdateIsDarkThemeUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
   suspend operator fun invoke(isDarkTheme: Boolean) {
        userPreferencesRepository.updateIsDarkTheme(isDarkTheme)
    }
}