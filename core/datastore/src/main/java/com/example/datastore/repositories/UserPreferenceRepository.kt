package com.example.datastore.repositories

import com.example.datastore.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    val userPreferencesFlow: Flow<UserPreferences>

    suspend fun updateIsDarkTheme(isDarkTheme:Boolean)

    suspend fun updateToken(token:String)


}