package com.example.domain.repositories.local

import com.example.model.user_preferences.UserPreferencesDataStore
import com.example.utility.network.Result
import com.example.utility.network.rootError
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val userPreferencesDataStoreFlow: Flow<UserPreferencesDataStore>

    suspend fun updateIsDarkTheme(isDarkTheme:Boolean)

    suspend fun updateShowPermissionCard(showPermissionCard:Boolean)

    suspend fun updateToken(token:String?)

    suspend fun <T,E: rootError> executeWithValidToken(
        action: suspend (token: String) -> Result<T, E>
    ):Result<T, E>



    suspend fun <T> executeFlowWithValidToken(
        action: suspend (token: String) -> Flow<T>
    ): Flow<T>
}