package com.example.domain.use_cases.user_preferences

import android.net.Uri
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.file.ProgressUpdate
import com.example.model.user_preferences.UserPreferences
import kotlinx.coroutines.flow.Flow

class GetUserPreferencesUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
   operator fun invoke(): Flow<UserPreferences> {
        return userPreferencesRepository.userPreferencesDataStoreFlow
    }

}