package com.example.data.repositories.user_preferences

import android.util.Log
import com.example.data.mapper.user_preferences.toUserPreferences
import com.example.datastore.service.UserPreferencesService
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.user_preferences.UserPreferencesDataStore
import com.example.utility.network.Error
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val userPreferencesService: UserPreferencesService
) : UserPreferencesRepository {
    override val userPreferencesDataStoreFlow: Flow<UserPreferencesDataStore>
        get() = userPreferencesService.userPreferencesDataStoreFlow.map { userPreferencesDataStore ->
            userPreferencesDataStore.toUserPreferences()
        }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) =
        userPreferencesService.updateIsDarkTheme(isDarkTheme)

    override suspend fun updateShowPermissionCard(showPermissionCard: Boolean) =
        userPreferencesService.updateShowPermissionCard(showPermissionCard)

    override suspend fun updateToken(token: String?) =
        userPreferencesService.updateToken(token)

    override suspend fun <T> executeWithValidToken(action: suspend (String) -> Result<T, rootError>): Result<T, rootError> {
        val token = this.userPreferencesDataStoreFlow.firstOrNull()?.token
        return if (token == null) {
            Result.Error(NetworkError.UNAUTHORIZED)
        } else {
            action(token)
        }
    }

    override suspend fun <T> executeWithValidTokenNetwork(action: suspend (String) -> Result<T, NetworkError>): Result<T, NetworkError> {
        val token = this.userPreferencesDataStoreFlow.firstOrNull()?.token
        return if (token == null) {
            Result.Error(NetworkError.UNAUTHORIZED)
        } else {
            action(token)
        }
    }

    override suspend fun <T> executeFlowWithValidToken(
        action: suspend (String) -> Flow<T>
    ): Flow<T> {
        val token = this.userPreferencesDataStoreFlow.firstOrNull()?.token
        return if (token == null) {
            throw Exception(NetworkError.UNAUTHORIZED.name)
        } else {
            action(token)
        }
    }
}