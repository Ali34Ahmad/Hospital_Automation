package com.example.domain.use_cases.user_preferences

import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.user_preferences.UserPreferencesDataStore
import kotlinx.coroutines.flow.Flow

class GetUserPreferencesUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
   operator fun invoke(): Flow<UserPreferencesDataStore> {
        return userPreferencesRepository.userPreferencesDataStoreFlow
    }

}