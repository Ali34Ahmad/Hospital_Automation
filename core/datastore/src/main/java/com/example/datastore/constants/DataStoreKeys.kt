package com.example.datastore.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    const val PREFERENCES_FILE_NAME= "user_preferences"
    val IS_DARK_THEME= booleanPreferencesKey("is_dark_theme")
    val AUTH_TOKEN= stringPreferencesKey("auth_token")
}