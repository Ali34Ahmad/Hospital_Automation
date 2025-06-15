package com.example.domain.use_cases.user_preferences

import android.net.Uri
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UpdateIsDarkThemeUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
   suspend operator fun invoke(isDarkTheme: Boolean) {
        userPreferencesRepository.updateIsDarkTheme(isDarkTheme)
    }

}